package com.security.web.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 自定义登录失败后的处理，返回一个json对象
 * @author YanShen.Wu
 * @date 2018/5/21 11:11:26
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap();
        map.put("flag","ok");
        map.put("ip",getIpAddress(request));
        String rep = JSON.toJSONString(map);
        writer.write(rep);
        writer.flush();
        writer.close();
    }

    /**
     * 获取当前请求的ip地址
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
