package com.security.web.mapper;

import com.security.web.entity.SysPermission;
import com.security.web.entity.SysUser;

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
     * 更新用户名的密码
     * @param user
     */
    void updateUserPassword(SysUser user);

}
