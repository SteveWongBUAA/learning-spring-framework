package com.vava.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 */
public class Person {
    @Value("张三")
    private String name;
    @Value("#{20-1}")
    private Integer age;

    public enum Sex {
        MALE,
        FEMAILE
    }

    private Sex sex;

    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Person(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public static List<Person> getPersons() {
        return Arrays.asList(
                new Person("张三", 20, Sex.MALE),
                new Person("李四", 30, Sex.MALE),
                new Person("王小花", 40, Sex.FEMAILE)
        );
    }
}
