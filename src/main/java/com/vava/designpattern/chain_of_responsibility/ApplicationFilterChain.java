package com.vava.designpattern.chain_of_responsibility;

import org.springframework.util.CollectionUtils;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 */
public class ApplicationFilterChain implements FilterChain {
    private static final int INCREMENT = 1;
    // 当前执行到哪个filter
    private int pos = 0;
    // filter的个数
    private int n = 0;
    private Filter[] filters = new Filter[1];

    @Override
    public void doFilter(Request request, Response response) {
        if (pos < n) {
            Filter filter = filters[pos];
            System.out.println("get NO." + pos + " filter: " + filter);
            pos++;
            // 递归，核心！！！！！！！
            filter.doFilter(request, response, this);
        } else {
            System.out.println("now let's do some service");
        }
    }

    public void addFilter(Filter filter) {
        // filter查重
        for (Filter iterFilter : filters) {
            if (iterFilter == filter) {
                return;
            }
        }

        if (n == filters.length) {
            System.out.println("filters expanding... now size = " + n);
            Filter[] newFilters = new Filter[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }

        filters[n++] = filter;
    }

}
