package com.web.security.config.springSecurityConfig;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    boolean canAccess(HttpServletRequest request,Authentication authentication);
}
