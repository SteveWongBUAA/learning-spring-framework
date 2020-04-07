package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public class LogInterceptor implements Interceptor{
    @Override
    public boolean preHandle(Request request, Response response, HandleExecutionChain handleExecutionChain) {
        System.out.println("LogInterceptor: preHandle");
        return true;
    }

    @Override
    public void postHandle(Request request, Response response, HandleExecutionChain handleExecutionChain) {
        System.out.println("LogInterceptor: postHandle");
    }

    @Override
    public void afterCompletion(Request request, Response response, HandleExecutionChain handleExecutionChain) {
        System.out.println("LogInterceptor: afterCompletion");
    }
}
