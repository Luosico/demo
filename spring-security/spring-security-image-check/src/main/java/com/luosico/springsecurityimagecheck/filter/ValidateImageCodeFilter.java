package com.luosico.springsecurityimagecheck.filter;

import com.luosico.springsecurityimagecheck.controller.ValidateImageCodeController;
import com.luosico.springsecurityimagecheck.domain.ImageCode;
import com.luosico.springsecurityimagecheck.exception.ValidateImageCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/25
 * <p>
 * 验证码校验过程应该是在UsernamePasswordAuthenticationFilter过滤器之前的
 * 即只有验证码校验通过后采去校验用户名和密码
 * 由于Spring Security并没有直接提供验证码校验相关的过滤器接口
 * 所以我们需要自己定义一个验证码校验的过滤器ValidateCodeFilter
 */
@Component
public class ValidateImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //确保请求URL为 /login 以及 请求方法为 POST
        if (StringUtils.equalsIgnoreCase("/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
            try {
                //验证图形验证码
                validateCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidateImageCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 验证验证传来的图形验证码
     *
     * @param servletWebRequest
     * @throws ServletRequestBindingException
     */
    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateImageCodeController.SESSION_KEY_IMAGE_CODE);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");


        if (codeInRequest.isEmpty()) {
            throw new ValidateImageCodeException("验证码不能为空！");
        }
        if (codeInSession == null) {
            throw new ValidateImageCodeException("验证码不存在！");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateImageCodeController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateImageCodeException("验证码已过期！");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateImageCodeException("验证码不正确！");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateImageCodeController.SESSION_KEY_IMAGE_CODE);
    }
}
