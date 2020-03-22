package com.vava.designpattern.strategy;

/**
 * @author Steve
 * Created on 2020-03
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("algorithm A");
    }
}
