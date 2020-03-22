package com.vava.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author steve
 * Created on 2020-03-09
 */
@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.vava.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    ;

    @Before("pointCut()")
    public void methodStart(JoinPoint joinPoint) {
        System.out.println("方法" + joinPoint.getSignature().getName() + ", 参数：" + Arrays
                .asList(joinPoint.getArgs()) + "开始:{}");
    }

    @After("com.vava.aop.LogAspects.pointCut()")
    public void methodEnd() {
        System.out.println("方法结束:{}");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void methodRet(Object result) {
        System.out.println("方法正常返回:" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void methodException(Exception exception) {
        System.out.println("方法异常返回:" + exception);
    }

}
