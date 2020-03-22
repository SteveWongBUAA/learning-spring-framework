package com.vava.lambda;

import com.vava.bean.Person;

/**
 * @author steve
 * Created on 2020-03-10
 */
@FunctionalInterface
public interface CheckPerson {
    boolean check(Person person);
}
