package com.web.security.config;

import com.web.security.entity.SysPermission;
import com.web.security.entity.SysUser;
import com.web.security.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 从数据库获取用户信息
 * @author YanShen.Wu
 * @date 2018-05-23 22:48
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户
        SysUser user = userDao.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("该户名不存在");
        }

        //获取用户权限
        List<SysPermission> list = userDao.findPermissionByUsername(username);
        //将权限放到用户中
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(SysPermission permission: list){
            authorityList.add(new SimpleGrantedAuthority(permission.getPermissionurl()));
        }
        user.setAuthorities(authorityList);

        return user;
    }
}
