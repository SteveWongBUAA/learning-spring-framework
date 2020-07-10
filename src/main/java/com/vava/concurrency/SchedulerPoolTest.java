package com.vava.concurrency;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Steve
 * Created on 2020-06
 */
@Component
public class SchedulerPoolTest {

    @Scheduled(cron = "0 * * * * ?")
    public void task1(){
        System.out.println("test");
        Thread thread =  Thread.currentThread();
        System.out.println("ThreadName:" + thread.getName() + ",id:" + thread.getId() + ",group:" + thread.getThreadGroup());

    }

    @Scheduled(fixedDelay = 5000)
    public void task2(){
        System.out.println("test");
        Thread thread =  Thread.currentThread();
        System.out.println("ThreadName:" + thread.getName() + ",id:" + thread.getId() + ",group:" + thread.getThreadGroup());

    }

}
