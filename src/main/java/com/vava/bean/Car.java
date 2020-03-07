package com.vava.bean;

/**
 */
public class Car {
    public Car() {
        System.out.println("Car construct");
    }

    public void init() {
        System.out.println("Car init");
    }

    public void destroy() {
        System.out.println("Car destroy");
    }
}
