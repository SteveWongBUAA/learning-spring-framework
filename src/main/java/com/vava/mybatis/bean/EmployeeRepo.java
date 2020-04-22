package com.vava.mybatis.bean;

/**
 * @author Steve
 * Created on 2020-04
 */
public class EmployeeRepo extends HumanResource {

    public EmployeeRepo(long id, long parentDepartmentId, String employeeName) {
        super(id, parentDepartmentId, employeeName);
    }

}
