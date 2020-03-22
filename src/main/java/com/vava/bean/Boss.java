package com.vava.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 * Created on 2020-03-09
 */
//默认加在IoC容器的组件，容器启动会调用无参构造器，进行初始化赋值等操作
@Component
public class Boss {
    //构造器要用的组件也是从容器中获取
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss有参构造器");
    }

    private Car car;

    public Car getCar() {
        return car;
    }

//    @Autowired
    //标注在方法：Soring容器创建当前对象，就会调用方法完成赋值
    //方法使用的参数，自定义类型的值从IoC容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

}
