package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.conf.MainConfigOfProfile;

/**
 * @author steve
 */
public class IocTestProfile {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("dev", "prod");
        applicationContext.register(MainConfigOfProfile.class);
        applicationContext.refresh();
        printBeans(applicationContext);
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
