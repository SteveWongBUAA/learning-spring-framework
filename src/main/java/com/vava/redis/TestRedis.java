package com.vava.redis;

/**
 * @author Steve
 * Created on 2020-05
 */
public class TestRedis {
    /**
     * 如何解决缓存击穿问题
     * https://zhuanlan.zhihu.com/p/75588064
     */
    public String get(String key) throws InterruptedException {
        String ret = Redis.get(key);
        if (ret == null) {
            // 缓存失效了
            if (Redis.setNx("mutex" + key, "1") == 1) {
                // 获取锁成功 才能查数据库
                ret = Db.query();
                Redis.set(key, ret);
                Redis.del("mutex" + key);
            } else {
                Thread.sleep(100);
                // 获取锁失败 歇一会再重新查
                return get(key);
            }
        }
        return ret;
    }
}
