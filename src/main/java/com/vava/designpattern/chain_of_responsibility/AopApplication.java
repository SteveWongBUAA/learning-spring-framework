package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopApplication {
    public static void main(String[] args) {
        AopChain aopChain = new AopChain();
        Object ret = aopChain.proceed();
        System.out.println("return: " + ret);
    }
}
