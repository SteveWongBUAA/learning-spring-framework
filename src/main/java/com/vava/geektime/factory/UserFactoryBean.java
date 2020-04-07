package com.vava.geektime.factory;

import org.springframework.beans.factory.FactoryBean;

import com.vava.geektime.User;

/**
 * @author Steve
 * Created on 2020-03
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
