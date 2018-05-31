package com.web.security.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YanShen.Wu
 * @date 2018-05-17 01:02
 */
public class SysUser implements UserDetails,Serializable {
    private Integer userid; //int(10) NOT NULL,
    private String username; //varchar(50) DEFAULT NULL,
    private String realname; //varchar(50) DEFAULT NULL,
    private String password; //varchar(50) DEFAULT NULL,
    private Date createdate; //date DEFAULT NULL,
    private Date lastlogintime; //date DEFAULT NULL,
    private boolean isuse; //int(5) DEFAULT NULL,
    private boolean isexpired; //int(5) DEFAULT NULL,
    private boolean islocked; //int(5) DEFAULT NULL,
    private boolean certificateisexpired; //int(5) DEFAULT NULL,

    // 用 户 拥 有 的 所 有 权 限
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public boolean isIsuse() {
        return isuse;
    }

    public void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }

    public boolean isIsexpired() {
        return isexpired;
    }

    public void setIsexpired(boolean isexpired) {
        this.isexpired = isexpired;
    }

    public boolean isIdlocked() {
        return islocked;
    }

    public void setIdlocked(boolean islocked) {
        this.islocked = islocked;
    }

    public boolean isCertificateisexpired() {
        return certificateisexpired;
    }

    public void setCertificateisexpired(boolean certificateisexpired) {
        this.certificateisexpired = certificateisexpired;
    }

    @Override
    public boolean isEnabled() {
        return isuse;
    }

    public void setEnabled(boolean enabled) {
        this.isuse = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isexpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.isexpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return islocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.islocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return certificateisexpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.certificateisexpired = credentialsNonExpired;
    }


}