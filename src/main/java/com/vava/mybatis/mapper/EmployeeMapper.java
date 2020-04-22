package com.vava.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.vava.mybatis.bean.EmployeeRepo;

public interface EmployeeMapper {
    EmployeeRepo getEmployeeById(Integer id);

    int addEmployee(EmployeeRepo employeeRepo);

    int updateEmployee(EmployeeRepo employeeRepo);

    int delEmployeeById(Integer id);

    @Select("select id from tbl_employee where department_id=#{id}")
    List<Long> getDepartmentEmployeeIds(long id);

    @Select("select name from tbl_employee where id=#{employeeId}")
    String getEmployeeNameById(Long employeeId);

    List<EmployeeRepo> getDepartmentEmployees(long id);
}
