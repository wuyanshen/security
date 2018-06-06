package com.security.web.service;

import com.security.web.entity.SysPermission;
import com.security.web.entity.SysUser;
import com.security.web.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YanShen.Wu
 * @date 2018-05-17 01:59
 */
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = Logger.getLogger(MyUserDetailService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根 据 用 户 名 查 询 用 户 信 息
        SysUser user = userMapper.findByUsername(username);
        // 根 据 用 户 名 查 询 当 前 用 户 所 有 权 限
        List<SysPermission> permList = userMapper.findPermissionByUsername(username);
        //authorities ： 存 放 所 有 用 户 权 限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (SysPermission perm : permList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(perm.getPermissionurl());
            authorities.add(authority);
        }
        // 把 所 有 权 限 赋 值 给 user
        user.setAuthorities(authorities);
        logger.info("当前用户：" + user);
        return user;
    }
}

