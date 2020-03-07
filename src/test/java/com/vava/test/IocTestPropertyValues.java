package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.bean.Person;
import com.vava.conf.MainConfigOfPropertyValues;

/**
 * @author steve
 */
public class IocTestPropertyValues {
    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
        System.out.println("############");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);


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
