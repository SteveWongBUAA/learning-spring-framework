package com.vava.redis;

/**
 * @author Steve
 * Created on 2020-07
 */
public class Jedis {
    public Redis redis;

    public Jedis() {
        redis = new Redis();
    }

    public Redis get() {
        return redis;
    }
}
