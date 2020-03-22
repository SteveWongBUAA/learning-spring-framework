package com.vava.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Steve
 * Created on 2020-03
 *
 * 声明式事务
 *
 * 环境搭建：
 * 1、导入相关依赖
 * ** 数据源、数据库驱动、Spring-jdbc模块
 * 2、配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 * 3、给方法标注 @Transactional 表示当前方法是一个事务方法
 * 4、@EnableTransactionManagement 开启基于注解的事务管理功能
 * 5、配置事务管理器来管理事务 {@link org.springframework.transaction.PlatformTransactionManager}
 *
 * 原理
 * 1、@EnableTransactionManagement 利用
 */
@EnableTransactionManagement
@ComponentScan({"com.vava.controller", "com.vava.service", "com.vava.dao"})
@Configuration
public class TxConfig {
    // 数据源
    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("test");
        dataSource.setPassword("123456");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc://mysql://localhost:3306/test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        // Spring 对Configuration类会特殊处理，给容器加组件的方法，都是从容器中找
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // 注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

}
