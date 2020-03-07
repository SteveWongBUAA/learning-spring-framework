package com.vava.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.vava.bean.Person;
import com.vava.conf.Config2;

/**
 *
 */
public class IoCTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config2.class);

    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("####### all beans: ");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        // 工厂bean获取的是getObject创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean);
        System.out.println(colorFactoryBean == colorFactoryBean2);

        // 取工厂bean本身
        Object colorFactoryBeanOri = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBeanOri);

    }

    @Test
    public void testImport() {
        printBeans(applicationContext);
    }

    @Test
    public void test01() {
        System.out.println("IoC容器创建完成");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        Object bean = applicationContext.getBean("personInConfQitian");
        Object bean2 = applicationContext.getBean("personInConfQitian");
    }

    @Test
    public void test03() {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);

        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForType(Person.class);
        for (String name : beanNamesForAnnotation) {
            System.out.println(name);
        }
        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

}
