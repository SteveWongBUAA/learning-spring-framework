package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vava.bean.Boss;
import com.vava.bean.Car;
import com.vava.bean.Color;
import com.vava.bean.Person;
import com.vava.conf.MainConfigOfAutowired;
import com.vava.conf.MainConfigOfPropertyValues;
import com.vava.dao.BookDao;
import com.vava.service.BookService;

/**
 * @author steve
 */
public class IocTestAutowired {
    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

    @Test
    public void test01() {
        System.out.println("############");
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
        bookService.print();

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        System.out.println("IoC容器：" + applicationContext);



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
