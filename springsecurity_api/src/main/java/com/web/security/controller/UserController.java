package com.web.security.controller;

import com.web.security.entity.User;
import com.web.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){
        int result = userService.addUser(user);
        return result;
    }
    @ResponseBody
    @PostMapping("/all/{pageNum}/{pageSize}")
    public Object findAllUser(@PathVariable("pageNum")int pageNum,@PathVariable("pageSize") int pageSize){
        List<User> list = userService.findAllUser(pageNum,pageSize);
        return list;
    }
}
