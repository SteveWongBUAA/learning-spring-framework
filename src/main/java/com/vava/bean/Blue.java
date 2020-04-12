package com.vava.bean;

/**
 * @author steve
 */
public class Blue {
    private int a;
    private int b;

    public Blue() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Blue{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
