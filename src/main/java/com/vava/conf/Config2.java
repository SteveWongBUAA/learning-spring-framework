package com.vava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.vava.bean.Color;
import com.vava.bean.ColorFactoryBean;
import com.vava.bean.Person;
import com.vava.bean.Red;
import com.vava.condition.LinuxCondition;
import com.vava.condition.MacOsCondition;
import com.vava.condition.MyImportBeanDefinitionRegistrar;
import com.vava.condition.MyImportSelector;

/**
 *
 */
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class Config2 {
    @Bean
    @Lazy
    public Person personInConfQitian() {
        System.out.println("给容器增加齐天bean");
        return new Person("qitain", 2);
    }

    @Bean("vava")
    public Person vava() {
        return new Person("vava", 1);
    }

    @Bean("vava2")
    public Person vava2() {
        return new Person("vava2", 1);
    }

    /**
     * conditional, 满足条件才会创建bean
     */
    @Conditional({MacOsCondition.class})
    @Bean("Jobs")
    public Person jobs() {
        return new Person("Steve Jobs", 40);
    }

    @Conditional({LinuxCondition.class})
    @Bean("Linus")
    public Person linus() {
        return new Person("Linus", 50);
    }

    /**
     * 给容器中注册组件
     * 1、包扫描+组件标注注解 （@Controller/@Service/@Repository）
     * 2、@Bean 导入的第三方包的组件
     * 3、@Import 快速给组件导入
     * 3.1.直接导入
     * 3.2. {@link MyImportSelector} 返回要导入的组件的全类名
     * 3.3. {@link MyImportBeanDefinitionRegistrar} 手动注册
     * 4、使用Spring提供的 {@link org.springframework.beans.factory.FactoryBean}  工厂Bean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
