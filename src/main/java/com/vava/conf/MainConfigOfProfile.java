package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vava.bean.CustomDataSource;

/**
 * Spring提供的，可以根据当前环境，动态切换和激活一系列组件的功能
 * 开发环境、测试环境、生产环境
 * 数据源切换
 *
 * @author steve
 * Created on 2020-03-09
 */
@Configuration
public class MainConfigOfProfile {
    @Profile("test")
    @Bean
    public CustomDataSource testDataSource() {
        return new CustomDataSource("test");
    }

    @Profile("dev")
    @Bean
    public CustomDataSource devDataSource() {
        return new CustomDataSource("dev");
    }

    @Profile("prod")
    @Bean
    public CustomDataSource prodDataSource() {
        return new CustomDataSource("prod");
    }
}
