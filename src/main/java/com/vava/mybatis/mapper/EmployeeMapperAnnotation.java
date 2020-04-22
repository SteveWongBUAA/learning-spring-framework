package com.vava.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.vava.mybatis.bean.EmployeeRepo;

/**
 * @author Steve
 * Created on 2020-04
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where last_name=#{lastName}")
    EmployeeRepo getEmployeeByName(String lastName);
}
