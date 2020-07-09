package com.housaiying.shuselectdemo2.bean;

/**
 * Created by housaiying on 2020/1/8
 */
public class Dept {
    private String dept_id;
    private String name;
    private String parentID;

    /**
     * @param dept_id  部门ID
     * @param name     部门名称
     * @param parentID 父级部门ID
     */
    public Dept(String parentID, String dept_id, String name) {
        this.dept_id = dept_id;
        this.name = name;
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}
