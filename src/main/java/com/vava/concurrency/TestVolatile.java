package com.vava.concurrency;

/**
 * @author Steve
 * Created on 2020-04
 */
public class TestVolatile {
    int a, b, c;
    int x = 0;
    boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        TestVolatile testVolatile = new TestVolatile();
        new Thread(testVolatile::readThread).start();
        Thread.sleep(1000);
        new Thread(testVolatile::writeThread).start();

        //        new Thread(testVolatile::loopThread).start();
        //        Thread.sleep(1000);
        //        new Thread(testVolatile::stopThread).start();

    }


    public void writeThread() {
        a = 1;
        b = 1;
        c = 1;

        x = 1; // store写volatile变量x
    }

    // 写volatile变量之后有内存屏障 StoreLoad ////////////////
    // 读volatile变量之前有内存屏障 LoadLoad ////////////////

    public void readThread() {
        while (x == 0) {
//            int r2 = x;// 读volatile变量x
//            System.out.println(String.format("r2 = %d", r2));
//            System.out.println(String.format("%d, %d, %d", a, b, c));
        }
    }

    public void loopThread() {
        System.out.println("loopThread start");
        while (running) {

        }
        System.out.println("loopThread end");
    }

    public void stopThread() {
        running = false;
    }

}
