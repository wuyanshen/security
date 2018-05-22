package com.security.web.filter;

import com.security.web.Exception.ImageCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YanShen.Wu
 * @date 2018/5/21 14:43:36
 */
public class ImageCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler){
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURI().contains("/login")){
            try{


                    String imageCode = (String) request.getSession().getAttribute("imageCode");
                    String pageImageCode = request.getParameter("imageCode");

                    if(StringUtils.trim(pageImageCode)==null){
                        throw new ImageCodeException("验证码必须输入");
                    }

                    if(!imageCode.equalsIgnoreCase(pageImageCode)){
                        throw new ImageCodeException("您输入的验证码有误");
                    }


            }catch (AuthenticationException e){
                // 交给自定义 AuthentFailureHandler 处理
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        filterChain.doFilter(request,response);

    }
}
