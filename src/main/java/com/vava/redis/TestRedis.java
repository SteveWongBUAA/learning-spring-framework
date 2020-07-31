package com.vava.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Steve
 * Created on 2020-05
 */
@Slf4j
public class TestRedis {
    private static final int RULE_CHAIN_QL_EXPRESS_ZSET_EXPIRE_SECS = 1;
    private static final int RULE_CHAIN_QL_EXPRESS_ZSET_LOCK_EXPIRE_SECS = 1;
    private static final long GET_RULE_CHAIN_RETRY_INTERVAL = 10;
    private static final int GET_RULE_CHAIN_KEY_EXPIRE_SECS = 10;

    private Gson gson = new Gson();

    private Map<String, Object> lockMap;

    private RuleChainMapper readRuleChainMapper;

    private Redis redis = new Redis();
    private Jedis jedis = new Jedis();


    /**
     * 如何解决缓存击穿问题
     * https://zhuanlan.zhihu.com/p/75588064
     */
    public String get(String key) throws InterruptedException {
        String ret = redis.get(key);
        if (ret == null) {
            // 缓存失效了
            if (redis.setNx("mutex" + key, "1") == 1) {
                // 获取锁成功 才能查数据库
                ret = Db.query();
                redis.set(key, ret);
                redis.del("mutex" + key);
            } else {
                Thread.sleep(100);
                // 获取锁失败 歇一会再重新查
                return get(key);
            }
        }
        return ret;
    }

    private List<RuleChain> getRuleChainByRuleChainIdOrderByOrder(String ruleChainId) throws InterruptedException {
        List<RuleChain> ruleChains;
        String keyRuleChainQlExpressZSet = RedisConstant.RULE_CHAIN_QL_EXPRESS_ZSET_PREFIX + ruleChainId;
        Set<String> strRuleChainSet = jedis.get().zrange(keyRuleChainQlExpressZSet, 0, -1);
        if (CollectionUtils.isEmpty(strRuleChainSet)) {
            // 缓存失效了
            String lockKey = RedisConstant.RULE_CHAIN_QL_EXPRESS_ZSET_LOCK_PREFIX + ruleChainId;
            if (jedis.get().setnx(lockKey, RedisConstant.EXISTS_VAL) == 1) {
                // 获取锁成功 才能查数据库 锁自动释放
                jedis.get().expire(lockKey, RULE_CHAIN_QL_EXPRESS_ZSET_LOCK_EXPIRE_SECS);

                ruleChains = readRuleChainMapper.selectByRuleChainIdOrderByOrder(ruleChainId);
                // 设置redis
                jedis.get().del(keyRuleChainQlExpressZSet);
                for (RuleChain ruleChain : ruleChains) {
                    jedis.get().zadd(keyRuleChainQlExpressZSet, ruleChain.getOrder(), ruleChain.toString());
                }
                jedis.get().expire(keyRuleChainQlExpressZSet, RULE_CHAIN_QL_EXPRESS_ZSET_EXPIRE_SECS);

                // 解锁
                jedis.get().del(lockKey);
            } else {
                // 获取锁失败 歇一会再重新查
                Thread.sleep(100);
                return getRuleChainByRuleChainIdOrderByOrder(ruleChainId);
            }
        } else {
            ruleChains = strRuleChainSet.stream()
                    .map((String strRuleChain) -> gson.fromJson(strRuleChain, RuleChain.class))
                    .collect(Collectors.toList());
        }
        return ruleChains;
    }


    /**
     * 先查redis，没有的话查mysql，注意防止缓存击穿
     * 使用单机锁，不用分布式锁的原因：降低复杂度，防止分布式锁非原子操作死锁
     */
    private List<RuleChain> getRuleChainByRuleChainIdOrderByOrder1(String ruleChainId) throws InterruptedException {
        List<RuleChain> ruleChains;
        String keyRuleChainQlExpressZSet = RedisConstant.RULE_CHAIN_QL_EXPRESS_ZSET_PREFIX + ruleChainId;
        Set<String> strRuleChainSet = jedis.get().zrange(keyRuleChainQlExpressZSet, 0, -1);
        if (CollectionUtils.isEmpty(strRuleChainSet)) {
            // 缓存失效了
            String lockKey = ruleChainId;
            synchronized (lockMap.computeIfAbsent(lockKey, k -> new Object())) {
                // 获取锁成功 才能查数据库 锁自动释放
                ruleChains = readRuleChainMapper.selectByRuleChainIdOrderByOrder(ruleChainId);
                // 设置redis
                jedis.get().del(keyRuleChainQlExpressZSet);
                for (RuleChain ruleChain : ruleChains) {
                    jedis.get().zadd(keyRuleChainQlExpressZSet, ruleChain.getOrder(), ruleChain.toString());
                }
                jedis.get().expire(keyRuleChainQlExpressZSet, RULE_CHAIN_QL_EXPRESS_ZSET_EXPIRE_SECS);
            }
        } else {
            ruleChains = strRuleChainSet.stream()
                    .map((String strRuleChain) -> gson.fromJson(strRuleChain, RuleChain.class))
                    .collect(Collectors.toList());
        }
        return ruleChains;
    }

    /**
     * 先查redis，没有的话查mysql，注意防止缓存击穿(分布式锁)
     */
    private GetRuleChainResponse getRuleChainByIdWithCache(String ruleChainId, int retry) throws InterruptedException {
        retry--;
        if (retry < 0) {
            throw new RuntimeException("getRuleChainById: [" + ruleChainId + "] failed after retry");
        }
        GetRuleChainResponse getRuleChainResponse;
        String getRuleChainKey = RedisConstant.GET_RULE_CHAIN_KEY_PREFIX + ruleChainId;
        String strResponse = jedis.get().get(getRuleChainKey);
        if (null == strResponse) {
            // 缓存失效了
            log.info("cache invalid for:" + getRuleChainKey);
            String lockKey = RedisConstant.GET_RULE_CHAIN_KEY_LOCK_PREFIX + ruleChainId;
            if (jedis.get().setnx(lockKey, RedisConstant.EXISTS_VAL) == 1) {
                log.info("get lock ok for:" + getRuleChainKey);
                // 获取锁成功 才能查数据库 锁自动释放
                jedis.get().expire(lockKey, RULE_CHAIN_QL_EXPRESS_ZSET_LOCK_EXPIRE_SECS);
                // 读取数据库
                getRuleChainResponse = getRuleChainByIdFromDb(ruleChainId);
                // 设置redis
                jedis.get().setex(getRuleChainKey, GET_RULE_CHAIN_KEY_EXPIRE_SECS, gson.toJson(getRuleChainResponse));
                // 解锁
                jedis.get().del(lockKey);
            } else {
                // 获取锁失败 歇一会再重新查
                log.info("get lock failed for:" + getRuleChainKey);
                Thread.sleep(GET_RULE_CHAIN_RETRY_INTERVAL);
                return getRuleChainByIdWithCache(ruleChainId, retry);
            }
        } else {
            // 读取成功，反序列化后返回
            getRuleChainResponse = gson.fromJson(strResponse, GetRuleChainResponse.class);
        }
        return getRuleChainResponse;
    }

    private GetRuleChainResponse getRuleChainByIdFromDb(String ruleChainId) {
        return null;
    }
}
