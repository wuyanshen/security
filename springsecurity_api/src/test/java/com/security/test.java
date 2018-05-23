package com.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YanShen.Wu
 * @date 2018-05-23 22:59
 */
public class test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");
        System.out.println(pwd);
    }
}
