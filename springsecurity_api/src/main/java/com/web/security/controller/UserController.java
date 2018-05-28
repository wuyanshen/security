package com.web.security.controller;

import com.web.security.entity.MyUser;
import com.web.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(com.web.security.entity.User user){
        int result = userService.addUser(user);
        return result;
    }
    @ResponseBody
    @PostMapping("/all/{pageNum}/{pageSize}")
    public List<com.web.security.entity.User> findAllUser(@PathVariable("pageNum")int pageNum,@PathVariable("pageSize") int pageSize){
        List<com.web.security.entity.User> list = userService.findAllUser(pageNum,pageSize);
        return list;
    }

    @GetMapping("/gets")
    @ResponseBody
    //@AuthenticationPrincipal 注解是为了从security 中获取登录后的user 信息。
    //用户名密码是用base64 加密 原文为 admin:admin 即 用户名:密码  内容是放在request.getHeader 的 "authorization" 中
    public Map<String,Object> gets(@AuthenticationPrincipal User loginedUser){
        Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age","22");
        map.put("phone","1902222222");
        if (loginedUser != null) {
            map.put("user",loginedUser);
        }

        return map;
    }

   /* @RequestMapping("/findMyUser")
    @ResponseBody
    public MyUser findMyUser(String userName){
        com.web.security.entity.User user = userService.findUser(userName);
        MyUser u = new MyUser(user.getUsername(),user.getPassword());
        return u;
    }*/

}
