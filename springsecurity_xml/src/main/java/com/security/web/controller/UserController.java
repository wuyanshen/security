package com.security.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YanShen.Wu
 * @date 2018-05-16 00:16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger("test");

    @RequestMapping("/find")
    public String findUser(){
        return "user/userFind";
    }
    @RequestMapping("/update")
    public String updateUser(){
        return "user/userUpdate";
    }
    @RequestMapping("/add")
    public String addUser(HttpServletRequest request){
        String imageCode = (String)request.getSession().getAttribute("imageCode");
        request.setAttribute("imageCode",imageCode);
        return "user/userAdd";
    }
    @RequestMapping("/delete")
    public String addDelete(){
        return "user/userDelete";
    }
}
