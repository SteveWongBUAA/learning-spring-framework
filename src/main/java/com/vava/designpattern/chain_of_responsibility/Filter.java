package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
