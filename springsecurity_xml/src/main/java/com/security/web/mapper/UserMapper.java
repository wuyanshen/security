package com.security.web.mapper;

import com.security.web.entity.Permission;
import com.security.web.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     *
     查 询 当 前 用 户 对 象
     */
     User findByUsername(String username);
    /**
     *
     查 询 当 前 用 户 拥 有 的 权 限
     */
     List<Permission> findPermissionByUsername(String username);

}
