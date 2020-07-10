package com.vava.concurrency;

/**
 * @author Steve
 * Created on 2020-06
 */
public class WaitAndNotify {


    public static void main(String[] args) throws Exception {
        TestStack s = new TestStack();
        s.pop();
        s.push("a");

    }
}
