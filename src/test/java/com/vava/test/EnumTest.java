package com.vava.test;

import org.junit.Test;

import com.vava.enums.Singleton;
import com.vava.enums.SingletonIdGenerator;

/**
 * @author steve
 */
public class EnumTest {

    @Test
    public void test01() {
        System.out.println("进入测试方法");
        Singleton instance = Singleton.getInstance();
        System.out.println("单例：" + instance);
        instance = Singleton.getInstance();
        System.out.println("单例：" + instance);
    }

    @Test
    public void test02() {
        System.out.println("进入测试方法");
        SingletonIdGenerator singletonIdGenerator = SingletonIdGenerator.INSTANCE;
        System.out.println("获取instance:" + singletonIdGenerator.hashCode() +  "id:" +singletonIdGenerator.getId());
        singletonIdGenerator = SingletonIdGenerator.INSTANCE;
        System.out.println("获取instance:" + singletonIdGenerator.hashCode() +  "id:" +singletonIdGenerator.getId());
    }
}
