package com.vava.designpattern.chain_of_responsibility;

import java.util.ArrayList;
import java.util.List;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public class HandleExecutionChain {
    //    private final Object handler;
    private List<Interceptor> interceptors = new ArrayList<>();

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    boolean applyPreHandle(Request request, Response response) {
        for (Interceptor interceptor : interceptors) {
            if (!interceptor.preHandle(request, response, this)) {
                triggerAfterComplete(request, response, null);
                return false;
            }
        }
        return true;
    }

    void applyPostHandle(Request request, Response response) {
        for (Interceptor interceptor : interceptors) {
            interceptor.postHandle(request, response, this);
        }
    }

    private void triggerAfterComplete(Request request, Response response, Object o) {
        for (Interceptor interceptor : interceptors) {
            interceptor.afterCompletion(request, response, this);
        }
    }
}
