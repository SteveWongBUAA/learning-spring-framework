package com.vava.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 *
 */
public class Dog {
    public Dog() {
        System.out.println("Dog construct");
    }

    @PostConstruct
    public void DogPostConstruct() {
        System.out.println("DogPostConstruct");
    }

    @PreDestroy
    public void DogPreDestroy() {
        System.out.println("DogPreDestroy");
    }
}
