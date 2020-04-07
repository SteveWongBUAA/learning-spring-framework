package com.vava.designpattern.chain_of_responsibility;

import com.vava.bean.Request;
import com.vava.bean.Response;

/**
 * @author Steve
 * Created on 2020-03
 *
 * 这个思路挺新颖啊，Spring AOP也是这么搞。
 * 挺难理解的
 * 首先有一个chain类，保存一个interceptor数组，顺序invoke interceptor，invoke的时候传入当前chain对象 invoke(this)
 * 每个intercetor也要配合，逻辑是
 * 1.do something before.
 * 2.调用chain.proceed().
 * 3.do something after.
 *
 * 怎么简化这个事情好理解一点呢
 * 极客时间教会你：
 *
 * 把interceptor的代码放到chain这里面来
 * proceed()
 * if 到头
 * ** 执行真正方法
 * 取第i个interceptor
 * proceed()
 *
 *
 */
public class AopChain {
    // 当前执行到哪个Interceptor
    private int pos = 0;
    // interceptor的个数
    private int n = 4;
    private AopInterceptor[] aopInterceptors = new AopInterceptor[n];

    public Object proceed() {
        if (pos == n) {
            return AopRealMethod.realMethod();
        }
        AopInterceptor aopInterceptor = aopInterceptors[pos++];
        // 递归，核心！！！！！！！
        return aopInterceptor.invoke(this);
    }

    public AopChain() {
        // 规定好的顺序
        aopInterceptors[0] = new AopAfterThrowing();
        aopInterceptors[1] = new AopAfterReturning();
        aopInterceptors[2] = new AopAfter();
        aopInterceptors[3] = new AopBefore();
    }
}
