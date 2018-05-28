package com.web.security.entity;

import java.io.Serializable;

/**
 * @author YanShen.Wu
 * @date 2018/5/22 18:00:57
 */
public class MyUser implements Serializable{

    private String username;
    private String password;

    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
