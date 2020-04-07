package com.vava.geektime.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vava.geektime.factory.DefaultUserFactory;
import com.vava.geektime.factory.UserFactory;

/**
 * @author Steve
 * Created on 2020-03
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanInitializationDemo.class);
        annotationConfigApplicationContext.refresh();

        UserFactory userFactory = annotationConfigApplicationContext.getBean(UserFactory.class);

        annotationConfigApplicationContext.close();

    }

    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
