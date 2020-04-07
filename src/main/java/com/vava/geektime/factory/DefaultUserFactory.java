package com.vava.geektime.factory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;


/**
 * @author Steve
 * Created on 2020-03
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法 UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet UserFactory 初始化中...");
    }
}
