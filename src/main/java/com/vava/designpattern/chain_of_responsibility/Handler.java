package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public abstract class Handler {
    protected Handler next = null;

    public void setNext(Handler next) {
        this.next = next;
    }

    public final void handle() {
        boolean handled = doHandle();
        if (next != null && !handled) {
            next.handle();
        }
    }

    protected abstract boolean doHandle();
}

