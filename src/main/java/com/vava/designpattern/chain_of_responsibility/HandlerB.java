package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class HandlerB extends Handler {
    @Override
    protected boolean doHandle() {
        boolean handled = false;
        System.out.println("HandlerB trying to handle...");
        return handled;
    }
}
