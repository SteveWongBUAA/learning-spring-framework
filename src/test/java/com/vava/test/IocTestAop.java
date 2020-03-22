package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.aop.MathCalculator;
import com.vava.bean.Boss;
import com.vava.bean.Car;
import com.vava.bean.Color;
import com.vava.conf.MainConfigOfAop;
import com.vava.conf.MainConfigOfAutowired;
import com.vava.service.BookService;

/**
 * @author steve
 */
public class IocTestAop {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        // 不能自己创建对象
        System.out.println("build Object by ourselves...");
        MathCalculator mathCalculator = new MathCalculator();
        mathCalculator.div(1, 1);
        // 从容器中取对象
        System.out.println("get Object by IoC...");
        mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1, 1);
        mathCalculator.div(1, 0);
        applicationContext.close();
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("####### all beans: ");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

}
