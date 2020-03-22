package com.vava.aop;

/**
 * @author steve
 * Created on 2020-03-09
 */
public class MathCalculator {
    public int div(int i, int j) {
        System.out.println("MathCalculator.div running");
        return i / j;
    }
}
