package com.web.security.util;

import com.web.security.entity.SysUser;
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
    public static SysUser getCurrentUser(){
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal!=null){
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails)principal;
                SysUser user = (SysUser)userDetails;
                return user;
            }
        }
        return null;
    }
}
