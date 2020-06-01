package com.vava.test;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author steve
 */
public class RedissonTest {

    @Test
    public void test01() {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("192.168.56.101:6379");

        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));

        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("myLock");
        lock.tryLock();


    }


}
