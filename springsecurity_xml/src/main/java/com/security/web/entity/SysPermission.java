package com.security.web.entity;

/**
 * @author YanShen.Wu
 * @date 2018-05-17 01:12
 */
public class SysPermission {
    private Integer permissionid; //int(10) NOT NULL,

    private String permissionname; //varchar(50) DEFAULT NULL,

    private String permissionurl; //varchar(50) DEFAULT NULL,

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getPermissionurl() {
        return permissionurl;
    }

    public void setPermissionurl(String permissionurl) {
        this.permissionurl = permissionurl;
    }


}
