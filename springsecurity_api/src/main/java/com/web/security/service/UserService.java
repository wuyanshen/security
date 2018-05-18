package com.web.security.service;

import com.web.security.entity.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    User findUser(String username);

    int insertUser(User user);

}
