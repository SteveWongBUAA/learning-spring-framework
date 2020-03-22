package com.vava.designpattern.eventbus;

import java.util.concurrent.Executor;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AsyncEventBus extends EventBus{
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
