package com.vava.designpattern.chain_of_responsibility;

/**
 * @author Steve
 * Created on 2020-03
 */
public class AopAfterReturning implements AopInterceptor {
    @Override
    public Object invoke(AopChain aopChain) {
        Object ret = aopChain.proceed();
        System.out.println("invoke afterReturning");
        return ret;
    }
}
