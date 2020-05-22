package com.vava.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Steve
 * Created on 2020-05
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                int t = (int) (Math.random() * 10000);
                System.out.println("sleep:" + t);
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return t;
            });
            completableFutures.add(completableFuture);
        }
//        CompletableFuture<Void> voidCompletableFuture =
//                CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
//        System.out.println("before join:" + (System.currentTimeMillis() - start));


//        voidCompletableFuture.join();
        for (CompletableFuture<Integer> future: completableFutures) {
            Integer integer = future.get();
            System.out.println("finally get: " + integer);
        }
        Long tt = System.currentTimeMillis() - start;
        System.out.println("cost:" + tt);
//        Thread.sleep(10000);
//        System.out.println(res);

        List<List<Integer>> res = new ArrayList<>();
        Map map = new HashMap<>();
    }
}
