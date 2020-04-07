package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopBefore implements AopInterceptor {
    @Override
    public Object invoke(AopChain aopChain) {
        System.out.println("invoke Before");
        return aopChain.proceed();
    }
}
