package com.web.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author YanShen.Wu
 * @date 2018-05-30 00:29
 */
@Controller
public class MainController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String userLogin(){
        return "login";
    }
}
