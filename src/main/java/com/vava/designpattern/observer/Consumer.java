package com.vava.designpattern.observer;

/**
 * @author steve
 * Created on 2020-03-11
 */
public class Consumer implements Observer {

    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Message message) {
        System.out.println("consumer " + name + " update message: " + message);
    }
}
