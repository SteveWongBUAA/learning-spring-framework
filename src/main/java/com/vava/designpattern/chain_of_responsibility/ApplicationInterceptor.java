package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public class ApplicationInterceptor {
    public static void main(String[] args) {
        HandleExecutionChain handleExecutionChain = new HandleExecutionChain();
        handleExecutionChain.addInterceptor(new LogInterceptor());
        handleExecutionChain.addInterceptor(new HeaderInterceptor());
        boolean preProcess = handleExecutionChain.applyPreHandle(new Request(), new Response());
        if (preProcess) {
            System.out.println("####### all preProcess ok... ");
            System.out.println("####### serving... ");
            handleExecutionChain.applyPostHandle(new Request(), new Response());
        } else {
            System.out.println("####### not all preProcess ok... exit ");
        }

    }
}
