package com.security.web.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录失败后的处理，返回一个json对象
 * @author YanShen.Wu
 * @date 2018/5/21 11:49:15
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("loginFlag","false");
        map.put("errMessage",exception.getMessage());
        String rep = JSON.toJSONString(map);
        writer.write(rep);
        writer.flush();
        writer.close();

    }
}
