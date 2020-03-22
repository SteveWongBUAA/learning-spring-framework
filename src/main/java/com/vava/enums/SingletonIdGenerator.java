package com.vava.enums;

import java.util.concurrent.atomic.AtomicLong;

/**
 */
public enum SingletonIdGenerator {
    INSTANCE;

    static {
        System.out.println("静态域执行");
    }

    private AtomicLong id = new AtomicLong(0);

    SingletonIdGenerator() {
        System.out.println("创建枚举");
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
