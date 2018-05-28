package com.web.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.web.security.entity.SysUser;
import com.web.security.mapper.UserMapper;
import com.web.security.service.UserService;
import com.web.security.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响


    @Override
    public int addUser(SysUser user) {
        return 1;
    }

    @Override
//    @Cacheable("user")
    public List<SysUser> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        List<SysUser> list = null;

        //redis操作
        if(!RedisUtil.hasKey("users")){
            RedisUtil.setCacheObject("users",userMapper.findAllUser(),20);
            list = userMapper.findAllUser();
        }else {
            PageHelper.startPage(pageNum, pageSize);
            list =  RedisUtil.getCacheList("users");
        }

        return list;
    }

    @Override
    public SysUser findUser(String username) {
        SysUser user = null;

        //redis操作
        if(!RedisUtil.hasKey(username)){
            user = userMapper.findByUsername(username);
            RedisUtil.setCacheObject(username,user,60);
        }else {
            user = (SysUser) RedisUtil.getCacheObject(username,SysUser.class);
        }
        return user;
    }

    @Override
    public int insertUser(SysUser user) {

        //redis操作
       /* if(!redisUtil.haskey(user.getId()+"")){
            user = userMapper.selectByPrimaryKey(user.getId());
            redisUtil.setValue(user.getId()+"",user);
        }else {
            user = (User) redisUtil.getObject(user.getId()+"",User.class);
        }
        return userMapper.insert(user);*/
       return 1;
    }


}
