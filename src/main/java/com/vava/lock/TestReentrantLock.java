package com.vava.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Steve
 * Created on 2020-04
 */
public class TestReentrantLock {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread("t1") {
            public void run() {
                System.out.println("t1 try to lock...");
                reentrantLock.lock();
                doTask("t1");
                System.out.println("t1 unlock...");
                reentrantLock.unlock();
            }
        };
        Thread t2 = new Thread("t2") {
            public void run() {
                System.out.println("t2 try to lock...");
                reentrantLock.lock();
                doTask("t2");
                System.out.println("t2 unlock...");
                reentrantLock.unlock();
            }
        };
        Thread t3 = new Thread("t3") {
            public void run() {
                System.out.println("t3 try to lock...");
                reentrantLock.lock();
                doTask("t3");
                System.out.println("t3 unlock...");
                reentrantLock.unlock();
            }
        };

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void doTask(String name) {
        for (int i = 0; i < 3; i++) {
            System.out.println(name + " running..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " end..");
    }
}
