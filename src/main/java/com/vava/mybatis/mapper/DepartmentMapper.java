package com.vava.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.vava.mybatis.bean.DepartmentRepo;

public interface DepartmentMapper {
    DepartmentRepo getDepartmentById(Integer id);
    int insert(DepartmentRepo departmentRepo);
    int createNewTable();

    @Select("select id from departments where parent_department_id=#{id}")
    List<Long> getSubDepartmentIds(long id);

    List<DepartmentRepo> selectSubDepartments(long id);
}
