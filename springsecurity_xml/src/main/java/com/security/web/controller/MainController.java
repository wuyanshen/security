package com.security.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YanShen.Wu
 * @date 2018-05-16 00:16
 */
@Controller
public class MainController {

    private Logger log = Logger.getLogger("test");

    @RequestMapping("/user/index")
    public String toIndex(){
        return "user/index";
    }

    @RequestMapping("/userLogin")
    public String userLogin(){
        log.debug("登录了");
        log.info("登录了，啦啦啦");
        return "login";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
