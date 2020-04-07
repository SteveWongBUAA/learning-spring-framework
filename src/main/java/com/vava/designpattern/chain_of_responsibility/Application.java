package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 * <p>
 * link: https://time.geekbang.org/column/article/216278
 * <p>
 * Avoid coupling the sender of a request to its receiver by giving more than one object a chance
 * to handle the request. Chain the receiving objects and pass the request along the chain
 * until an object handles it.
 * <p>
 * 将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。将这些接收对象串成一条链，并沿着这条链传递这个请求，
 * 直到链上的某个接收对象能够处理它为止。
 */
public class Application {
    public static void main(String[] args) {
//        HandlerChain chain = new HandlerChain();
//        chain.addHandler(new HandlerA());
//        chain.addHandler(new HandlerB());
//        chain.handle();

        ApplicationFilterChain applicationFilterChain = new ApplicationFilterChain();
        applicationFilterChain.addFilter(new LogFilter());
        applicationFilterChain.addFilter(new LogFilter());
        applicationFilterChain.addFilter(new HeaderFilter());
        applicationFilterChain.doFilter(new Request(), new Response());

    }
}
