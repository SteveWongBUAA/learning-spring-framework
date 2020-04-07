package com.vava.geektime.factory;

import com.vava.geektime.User;

/**
 * {@link User} 工厂类
 *
 * @author Steve
 * Created on 2020-03
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }

    void initUserFactory();
}
