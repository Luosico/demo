package com.luosico.springsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/22
 * <p>
 * 认证通过后调用
 */

@Component
public class SuccessHandle implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("success: 认证通过了");
        logger.info("name: " + authentication.getName());
        logger.info("pwd: " + authentication.getCredentials());
        logger.info("details: " + authentication.getDetails());
        logger.info("Authorities: " + authentication.getAuthorities());
        logger.info("Principal: " + authentication.getPrincipal());
        httpServletResponse.sendRedirect("/main.html");
    }


}
