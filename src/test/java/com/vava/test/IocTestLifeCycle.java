package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.conf.MainConfigOfLifeCycle;

/**
 * @author steve
 */
public class IocTestLifeCycle {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成");

        applicationContext.close();


    }

}
