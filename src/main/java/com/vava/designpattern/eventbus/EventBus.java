package com.vava.designpattern.eventbus;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Steve
 * Created on 2020-03
 */
public class EventBus {
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

//    public EventBus() {
//        this(MoreExecutors.directExecutor());
//    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }
    }
}
