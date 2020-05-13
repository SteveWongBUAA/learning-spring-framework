package com.vava.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * Created on 2020-04
 */
public class TestCompletableFuture {
    public static void main(String[] args) throws Exception {
        // 有个command列表
        List<Integer> commands = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            commands.add(i);
        }
        long begin = System.currentTimeMillis();
        commands.parallelStream().forEach((i) -> {
            Boolean res = sendCommand(i);
            if (!res) {
                System.out.println("调用" + i + "失败。。干一些别的活");
            } else {
                System.out.println("调用" + i + "成功");
            }
        });
        System.out.println("并行搞，需要时间:" + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        commands.forEach((i) -> {
            Boolean res = sendCommand(i);
            if (!res) {
                System.out.println("调用" + i + "失败。。干一些别的活");
            } else {
                System.out.println("调用" + i + "成功");
            }
        });
        System.out.println("串行搞，需要时间:" + (System.currentTimeMillis() - begin));
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        //        Thread.sleep(2000);
    }

    static Boolean sendCommand(int i) {
        System.out.println("send command for " + i + "...");
        try {
            Thread.sleep((long) (1000));
        } catch (InterruptedException e) {
        }
        // 模拟有时候成功有时候失败
        return i % 3 == 0;
    }

}
