package com.vava.test;

import java.util.concurrent.Executors;

import org.junit.Test;

import com.vava.designpattern.eventbus.AsyncEventBus;
import com.vava.designpattern.eventbus.DObserver;
import com.vava.designpattern.eventbus.EventBus;
import com.vava.designpattern.eventbus.Pmsg;

/**
 * @author steve
 */
public class EventBusTest {

    private static final int DEFAULT_EVENTBUS_THREAD_NUMBER = 2;

    @Test
    public void test01() {
        // 1.创建EventBus, 注入线程池的大小，异步执行的时候会用
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_NUMBER));

        // 2.定义Observer, 利用注解标注
        DObserver observer = new DObserver();

        // 3.eventBus中注册Observer
        eventBus.register(observer);

        // 4.定义要发布的消息event
        Pmsg event = new Pmsg("i am an Pmsg event");
        System.out.println("eventBus post Pmsg:" + event.toString());
        // 5.发布消息
        eventBus.post(event);
    }
}
