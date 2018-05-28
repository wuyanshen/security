package com.web.security.service;

import com.web.security.entity.SysUser;

import java.util.List;

public interface UserService {
    int addUser(SysUser user);

    List<SysUser> findAllUser(int pageNum, int pageSize);

    SysUser findUser(String username);

    int insertUser(SysUser user);

}
