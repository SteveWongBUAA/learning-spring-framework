package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public class HeaderFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("HeaderFilter preProcess...");

        chain.doFilter(request, response);

        System.out.println("HeaderFilter postProcess...");
    }
}
