package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.vava.bean.Person;

/**
 * @author steve
 * Created on 2020-03-07
 */
@PropertySource(value = {"classpath:/person.properties"}, encoding = "UTF-8")
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
