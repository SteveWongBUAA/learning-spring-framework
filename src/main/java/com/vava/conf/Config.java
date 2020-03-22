package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.vava.bean.Person;
import com.vava.bean.Person.Sex;

/**
 *
 */
@Configuration
@ComponentScan(value = "com.vava", includeFilters = {
        //        @Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        //        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
        @Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, useDefaultFilters = false)
public class Config {
    @Bean
    public Person personInConf() {
        return new Person("vava", 2, Sex.FEMAILE);
    }
}
