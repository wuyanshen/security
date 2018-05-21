package com.security.web.controller;

import com.security.web.entity.User;
import com.security.web.util.SpringSecurityUtil;
import com.security.web.util.ValidateCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 生成验证码
     * @param time
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getCode")
    public void getCode(@RequestParam(value = "time") String time, HttpServletRequest request, HttpServletResponse response){
        ValidateCode code = new ValidateCode(60,20,4,30,15,"imageCode");
        code.getCode(request, response);
    }


    @RequestMapping(value="/getUser")
    @ResponseBody
    public User getUser(){
        User user = SpringSecurityUtil.getCurrentUser();
        return user;
    }
}
