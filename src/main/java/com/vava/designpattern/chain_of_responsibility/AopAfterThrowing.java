package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopAfterThrowing implements AopInterceptor {
    @Override
    public Object invoke(AopChain aopChain) {
        try {
            return aopChain.proceed();
        } catch (Exception e) {
            System.out.println("invoke afterThrowing method");
            throw e;
        }
    }
}
