package com.vava.designpattern.callback;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AClass {
    public static void main(String[] args) {
        System.out.println("程序启动");
        BClass b = new BClass();
        b.process(new ICallBack() {
            @Override
            public void methodToCallback() {
                System.out.println("call back me.");
            }
        });
    }

}
