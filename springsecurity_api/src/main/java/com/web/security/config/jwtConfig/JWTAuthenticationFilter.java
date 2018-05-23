package com.web.security.config.jwtConfig;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 请求过滤器
 * @author YanShen.Wu
 * @date 2018/5/22 17:56:08
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private String protectUrlPattern = "/api/**";



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        try {
//            if (isProtectedUrl(request)) {
                Authentication authentication = JWTUtil.getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
//            return;
//        }

        filterChain.doFilter(request,response);
    }

    /**
     * 是否是受保护的url
     * @param request
     * @return
     */
    private boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match(protectUrlPattern, request.getServletPath());
    }
}