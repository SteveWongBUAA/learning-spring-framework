package com.vava.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.vava.aop.MathCalculator;
import com.vava.conf.MainConfigOfAop;
import com.vava.conf.TxConfig;
import com.vava.service.UserService;

/**
 * @author steve
 */
public class IocTestTx {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
        applicationContext.close();
    }


}
