package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopRealMethod {
    public static Object realMethod() {
        System.out.println("真正的方法执行。。");
        throw new RuntimeException("exception");
//        return "ok";
    }
}
