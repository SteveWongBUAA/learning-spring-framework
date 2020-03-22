package com.vava.designpattern.eventbus;

/**
 * @author Steve
 * Created on 2020-03
 */
public class DObserver {
    @Subscribe
    public void f1(Pmsg event) {
        System.out.println("DObserver.f1 consume Pmsg event: " + event.toString());
    }
    @Subscribe
    public void f2(Qmsg event) {
        System.out.println("DObserver.f2 consume Qmsg event: " + event.toString());
    }
}
