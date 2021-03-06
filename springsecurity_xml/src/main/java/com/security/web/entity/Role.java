package com.security.web.entity;

/**
 * @author YanShen.Wu
 * @date 2018-05-17 01:11
 */
public class Role {
    private Integer roleid; //int(10) NOT NULL,
    private String rolename; //varchar(50) DEFAULT NULL
    private String roledesc; //varchar(50) DEFAULT NULL,

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }
}

