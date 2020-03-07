package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.vava.bean.Car;
import com.vava.bean.WholeLifeCycleBean;

/**
 * bean的生命周期
 * 创建 --  初始化 -- 销毁
 * 容器管理bean的生命周期，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 1.指定初始化和销毁方法 init-method  destroy-method
 * 2.通过实现
 * {@link org.springframework.beans.factory.InitializingBean}
 * {@link org.springframework.beans.factory.DisposableBean}
 * 3.使用JSR250:
 * {@link javax.annotation.PostConstruct} bean创建完成并且赋值完成后执行
 * {@link javax.annotation.PreDestroy} 从容器销毁bean之前
 * 4.{@link org.springframework.beans.factory.config.BeanPostProcessor}
 * 后置处理器
 */
@Configuration
@ComponentScan("com.vava.bean")
public class MainConfigOfLifeCycle {

//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    public Car car() {
//        return new Car();
//    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public WholeLifeCycleBean wholeLifeCycleBean() {
        WholeLifeCycleBean wholeLifeCycleBean = new WholeLifeCycleBean();
        wholeLifeCycleBean.setName("testName");
        return wholeLifeCycleBean;
    }

}
