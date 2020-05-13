package com.vava.concurrency.output1by1;

/**
 * @author Steve
 * Created on 2020-05
 *
 * https://www.bilibili.com/video/BV1PJ41117Q1?p=1
 */
public class SynchronizedDemo {
    static Thread t1 = null;
    static Thread t2 = null;
    static Object o = new Object();

    public static void main(String[] args) {
        char[] c1 = {'1', '2', '3', '4', '5', '6', '7'};
        char[] c2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        t1 = new Thread(() -> {
            synchronized (o) {
                for (char c : c1) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        });

        t2 = new Thread(() -> {
            synchronized (o) {
                for (char c : c2) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        });

        t1.start();
        t2.start();

    }

}
