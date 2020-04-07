package com.vava.designpattern.chain_of_responsibility;

/**
 * Created on 2020-03
 */
public interface AopInterceptor {
    Object invoke(AopChain aopChain);
}
