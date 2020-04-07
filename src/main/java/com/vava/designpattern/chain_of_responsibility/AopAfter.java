package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopAfter implements AopInterceptor {
    @Override
    public Object invoke(AopChain aopChain) {
        try {
            return aopChain.proceed();
        } finally {
            System.out.println("invoke After...");
        }
    }
}
