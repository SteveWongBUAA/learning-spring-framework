package com.vava.designpattern.callback;

/**
 * @author Steve
 * Created on 2020-03
 */
public class BClass {
    public void process(ICallBack callBack) {
        System.out.println("Bclass pre process");
        System.out.println("Bclass callback some methods");
        callBack.methodToCallback();
        System.out.println("Bclass post process");
    }
}
