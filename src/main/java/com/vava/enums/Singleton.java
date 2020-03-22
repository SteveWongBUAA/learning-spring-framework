package com.vava.enums;

/**
 * @author Steve
 * Created on 2020-03
 */
public class Singleton {
    private Singleton() {}

    private enum SingletonEnum {
        INSTANCE;

        private Singleton singleton;

        /**
         * 枚举创建的时候就会产生唯一的实例
         */
        SingletonEnum() {
            System.out.println("创建枚举，同时创建单例");
            singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }


    public static Singleton getInstance() {
        System.out.println("获取单例");
        return SingletonEnum.INSTANCE.getSingleton();
    }
}
