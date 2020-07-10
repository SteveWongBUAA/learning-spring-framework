package com.vava.concurrency;

import java.util.LinkedList;

/**
 * @author Steve
 * Created on 2020-06
 */
public class TestStack {

    LinkedList list = new LinkedList();

    public synchronized void push(Object x) {
        synchronized (list) {
            list.addLast(x);
            notify();
        }
    }

    public synchronized Object pop()
            throws Exception {
        synchronized (list) {
            if (list.size() <= 0) {
                wait();
            }
            return list.removeLast();
        }
    }
}
