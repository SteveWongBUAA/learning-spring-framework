package com.vava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.bean.Person;
import com.vava.conf.Config;

/**
 */
public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        Person bean = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(bean);
        String[] namesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }


    }
}
