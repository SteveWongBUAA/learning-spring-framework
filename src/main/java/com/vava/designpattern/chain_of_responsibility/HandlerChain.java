package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        // 新加的节点在链表尾部，next是null
        handler.setNext(null);

        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }

        tail.setNext(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}
