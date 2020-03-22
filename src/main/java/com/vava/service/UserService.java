package com.vava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vava.dao.UserDao;

/**
 * @author Steve
 * Created on 2020-03
 *
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.println("插入完成");
        int i = 10 / 0;
    }

}
