package com.security.web.Exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author YanShen.Wu
 * @date 2018/5/21 14:56:41
 */
public class ImageCodeException extends AuthenticationException {
    public ImageCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ImageCodeException(String msg) {
        super(msg);
    }
}
