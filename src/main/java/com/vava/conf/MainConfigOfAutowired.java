package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.vava.bean.Car;
import com.vava.bean.Color;
import com.vava.dao.BookDao;

/**
 * @author steve
 * Created on 2020-03-07
 * 1.Spring 利用依赖注入（DI），完成对IoC容器中各个组件中的依赖关系
 * 1.1.@Autowired : 默认优先按照类型去容器中找对应的组件
 * 1.5.@Primary 默认使用首选的bean
 * 但是仍然可以继续使用@Qulifier
 *
 * 2.Spring还支持使用@Resource（JSR250）和 @Inject （JSR330） [Java规范的注解]
 * - @Resource: 可以合Autowired一样实现自动装配功能，默认是按照组件名称实现装配。
 * -- 不能支持@Primary 不能支持 @Autowired(require=false)
 *
 * -@Inject:
 * -- 需要导入javax.inject的包，和Autowired功能一样，没有required=false的功能
 *
 * @Autowired 是Spring定义的；@Resource和@Inject 都是Java规范
 * {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}解析完成自动装配功能
 *
 * 3.@Autowired：构造器、参数、方法、属性
 * 3.1.标注在方法
 * 3.2.标在构造器，如果组件只有一个有餐构造器，这个有参构造器的@Autowired可以省略
 *
 * 4.自定义组件想要使用Spring容器底层的一些组件， ApplicationContext BeanFactory
 * 实现xxxAware即可(功能是使用xxxAwareProcessor实现的)
 *
 *
 *
 */
@Configuration
@ComponentScan({"com.vava.service", "com.vava.controller", "com.vava.dao", "com.vava.bean"})
public class MainConfigOfAutowired {
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    /**
     * @Bean 标注的方法创建对象的时候方法的参数值从容器中获取
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
