package com.vava.designpattern.observer;

/**
 * @author steve
 * Created on 2020-03-11
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(Message message);
}
