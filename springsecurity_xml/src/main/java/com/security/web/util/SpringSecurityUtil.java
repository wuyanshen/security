package com.security.web.util;

import com.security.web.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * SpringSecurity工具类
 * @author YanShen.Wu
 * @date 2018/5/21 17:06:28
 */
public class SpringSecurityUtil {


    /**
     * 获取当前登录用户
     * @return
     */
    public static User getCurrentUser(){
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal!=null){
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails)principal;
                User user = (User)userDetails;
                return user;
            }
        }
        return null;
    }
}
