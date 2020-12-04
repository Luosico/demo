package com.luosico.springsecurityimagecheck.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/25
 *
 * 验证码的异常类
 */
public class ValidateImageCodeException extends AuthenticationException {

    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateImageCodeException(String message) {
        super(message);
    }
}
