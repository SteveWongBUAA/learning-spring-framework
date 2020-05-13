package com.vava.concurrency.output1by1;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Steve
 * Created on 2020-05
 *
 * https://www.bilibili.com/video/BV1PJ41117Q1?p=1
 */
public class ParkDemo {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] c1 = {'1', '2', '3', '4', '5', '6', '7'};
        char[] c2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        t1 = new Thread(() -> {
            for (char c : c1) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char c : c2) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();

    }

}
