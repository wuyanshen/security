package com.web.security.config.springSecurityConfig;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户权限判断类
 * 对用户访问的资源判断是否有权访问
 * @author YanShen.Wu
 * @date 2018-05-31 23:04
 */
@Component("authService")
public class AuthServcieImpl implements AuthService{

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean canAccess(HttpServletRequest request, Authentication authentication) {
        Object principal =  authentication.getPrincipal();
        if(principal==null){
            return false;
        }

        if(principal instanceof AnonymousAuthenticationToken){
            //TODO: 这里要校验资源是否可以被匿名用户访问
            return false;
        }

        if(principal instanceof UserDetails){
            Set<String> authorities =  authentication.getAuthorities()
                    .stream()
                    .map(e->((GrantedAuthority) e).getAuthority())
                    .collect(Collectors.toSet());

            String currentURI = request.getRequestURI();
            for(String url:authorities){
                if(antPathMatcher.match(url,currentURI)){
                    return true;
                }
            }

        }

        return false;
    }
}
