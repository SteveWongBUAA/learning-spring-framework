package com.vava.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 所有生命周期能调用的都整一遍
 */
public class WholeLifeCycleBean implements InitializingBean, DisposableBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private String name;

    public WholeLifeCycleBean() {
        System.out.println("[WholeLifeCycleBean]: ==================");
        System.out.println("[WholeLifeCycleBean]: construct");
    }

    public void customInit() {
        System.out.println("[WholeLifeCycleBean]: @Bean initMethod=customInit");
    }

    public void setName(String name) {
        System.out.println("[WholeLifeCycleBean]: setName: " + name);
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[WholeLifeCycleBean]: implements InitializingBean.afterPropertiesSet");
    }

    public void customDestroy() {
        System.out.println("[WholeLifeCycleBean]: @Bean destroyMethod=customDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[WholeLifeCycleBean]: implements DisposableBean.destroy");
    }

    @PostConstruct
    public void customPostConstruct() {
        System.out.println("[WholeLifeCycleBean]: @PostConstruct");
    }

    @PreDestroy
    public void customPreDestroy() {
        System.out.println("[WholeLifeCycleBean]: @PreDestroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[WholeLifeCycleBean]: ApplicationContextAware.setApplicationContext");
        this.applicationContext = applicationContext;
    }
}
