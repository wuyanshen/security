package com.web.security.config.jwtConfig;

import com.alibaba.fastjson.JSON;
import com.web.security.entity.MyUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户登录校验类
 * @author YanShen.Wu
 * @date 2018/5/22 17:57:20
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)throws AuthenticationException, IOException, ServletException {

        // JSON反序列化成 MyUser
//        MyUser creds = new ObjectMapper().readValue(req.getInputStream(), MyUser.class);
        MyUser creds = JSON.parseObject(req.getInputStream(), MyUser.class);

        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res, FilterChain chain,Authentication auth) throws IOException, ServletException {
        //登录成功后返回token
        JWTUtil.addAuthentication(res, auth);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //登录失败返回失败信息
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String error = "";
        if(failed.getMessage().equals("Bad credentials")){
            error = "用户名或密码错误";
        }
        writer.write(JSONResult.fillResultString(500, error, null));
    }
}
