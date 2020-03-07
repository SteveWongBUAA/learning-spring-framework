package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.vava.dao.BookDao;

/**
 * @author steve
 * Created on 2020-03-07
 * Spring 利用依赖注入（DI），完成对IoC容器中各个组件中的依赖关系
 * 1.@Autowired : 默认优先按照类型去容器中找对应的组件
 * 5.@Primary 默认使用首选的bean
 * 但是仍然可以继续使用@Qulifier
 */
@Configuration
@ComponentScan({"com.vava.service", "com.vava.controller", "com.vava.dao"})
public class MainConfigOfAutowired {
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
}
