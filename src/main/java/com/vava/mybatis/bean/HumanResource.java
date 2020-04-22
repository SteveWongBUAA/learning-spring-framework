package com.vava.mybatis.bean;

/**
 * @author Steve
 * Created on 2020-04
 */

public abstract class HumanResource {
    protected long id;
    protected long parentDepartmentId;
    protected String name;

    public HumanResource(long id, long parentDepartmentId, String name) {
        this.id = id;
        this.name = name;
        this.parentDepartmentId = parentDepartmentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HumanResource{" +
                "id=" + id +
                ", parentDepartmentId=" + parentDepartmentId +
                ", name='" + name + '\'' +
                '}';
    }
}






