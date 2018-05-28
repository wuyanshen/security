package com.web.security.mapper;


import com.web.security.entity.SysPermission;
import com.web.security.entity.SysUser;

import java.util.List;


public interface UserMapper {
    /**
     *
     查 询 当 前 用 户 对 象
     */
     SysUser findByUsername(String username);
    /**
     *
     查 询 当 前 用 户 拥 有 的 权 限
     */
     List<SysPermission> findPermissionByUsername(String username);

    /**
     * 查询所有用户
     */
    List<SysUser> findAllUser();

}
