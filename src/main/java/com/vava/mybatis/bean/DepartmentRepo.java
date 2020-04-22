package com.vava.mybatis.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * Created on 2020-04
 */
public class DepartmentRepo extends HumanResource {
    private List<HumanResource> subNodes = new ArrayList<>();

    public DepartmentRepo(long id, long parentDepartmentId, String name) {
        super(id, parentDepartmentId, name);
    }

    public void addSubNode(HumanResource hr) {
        subNodes.add(hr);
    }
}
