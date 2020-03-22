package com.vava.ext;

import org.springframework.context.annotation.Configuration;

/**
 * @author Steve
 * Created on 2020-03
 *
 * 扩展原理
 * BeanPostProcessor bean后置处理器，bean创建对象初始化前后拦截
 * BeanFactoryPostProcessor BeanFactory的后置处理器
 * -- 在BeanFactory标准初始化之后（所有bean Definition都已经加载，但是没有bean被实例化instantiated）
 *
 * 1、IoC容器创建对象
 * 2、invokeBeanFactoryPostProcessor(beanFactory)
 * -- 如何找到所有的
 *
 *
 *
 */
@Configuration
public class ExtConfig {

}
