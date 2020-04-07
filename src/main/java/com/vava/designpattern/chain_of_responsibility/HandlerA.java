package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class HandlerA extends Handler {
    @Override
    protected boolean doHandle() {
        boolean handled = false;
        System.out.println("Handler A trying to handle..");
        return handled;
    }
}
