package com.web.security.config.jwtConfig;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author YanShen.Wu
 * @date 2018/5/22 18:01:29
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
