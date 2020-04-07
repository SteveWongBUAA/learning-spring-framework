package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * Created on 2020-03
 */
public interface Interceptor {
    boolean preHandle(Request request, Response response, HandleExecutionChain handleExecutionChain);

    void postHandle(Request request, Response response, HandleExecutionChain handleExecutionChain);

    void afterCompletion(Request request, Response response, HandleExecutionChain handleExecutionChain);
}
