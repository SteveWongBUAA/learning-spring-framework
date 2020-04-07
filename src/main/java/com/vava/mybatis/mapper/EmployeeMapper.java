package com.vava.mybatis.mapper;

import com.vava.mybatis.bean.Employee;

/**
 */
public interface EmployeeMapper {
    Employee getEmployeeById(Integer id);
    int addEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int delEmployeeById(Integer id);
}
