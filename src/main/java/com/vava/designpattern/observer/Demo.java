package com.vava.designpattern.observer;


/**
 * @author steve
 * Created on 2020-03-11
 */
public class Demo {
    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer1 = new Consumer("c1");
        Consumer consumer2 = new Consumer("c2");
        producer.registerObserver(consumer1);
        producer.registerObserver(consumer2);
        producer.notifyObserver(new Message("test content"));

    }
}
