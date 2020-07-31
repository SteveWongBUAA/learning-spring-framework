package com.vava.redis;

import java.util.Set;

/**
 * @author Steve
 * Created on 2020-05
 */
public class Redis {
    public String get(String key) {
        return null;
    }

    public int setNx(String key, String val) {
        return 0;
    }

    public void set(String key, String ret) {
    }

    public void del(String s) {
    }

    public Set<String> zrange(String keyRuleChainQlExpressZSet, int i, int i1) {
        return null;
    }

    public int setnx(String lockKey, Object existsVal) {
        return 1;
    }

    public void expire(String lockKey, int ruleChainQlExpressZsetLockExpireSecs) {
    }

    public void zadd(String keyRuleChainQlExpressZSet, int order, String toString) {
    }

    public void setex(String key, int time, String val) {
    }
}
