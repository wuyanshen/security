package com.web.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.web.security.entity.User;
import com.web.security.mapper.UserMapper;
import com.web.security.service.UserService;
import com.web.security.util.redis.RedisUtil;
import com.web.security.util.redis.ReedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int addUser(User user) {
        return 1;
    }

    @Override
//    @Cacheable("user")
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        List<User> list = null;
        PageHelper.startPage(pageNum, pageSize);

        //redis操作
        if(!redisUtil.haskey("users")){
            redisUtil.setValue("users",userMapper.findAllUser());
            list = userMapper.findAllUser();
        }else {
            list =  redisUtil.getList("users",User.class);
        }

        return list;
    }

    @Override
    public User findUser(String username) {
        User user = null;

        //redis操作
        if(!redisUtil.haskey(username)){
            user = userMapper.findByUsername(username);
            redisUtil.setValue(username,user);
        }else {
            user = (User) redisUtil.getObject(username,User.class);
        }
        return user;
    }

    @Override
    public int insertUser(User user) {

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
