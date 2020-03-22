package com.vava.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 * Created on 2020-03-11
 */
public class Producer implements Subject{
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(Message message) {
        for (Observer observer: observerList) {
            observer.update(message);
        }
    }
}
