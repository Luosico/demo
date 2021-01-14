package com.luosico.common.service;

import com.luosico.common.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/18
 */

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    //加密解析器
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);


    /**
     * 对信息进行认证
     *
     * @param authentication 未认证信息
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的用户名
        String username = authentication.getName();
        //获取输入的明文
        String password = (String) authentication.getCredentials();

        logger.info("name = " + authentication.getName());
        logger.info("Credentials = " + authentication.getCredentials());
        logger.info("Details = " + authentication.getDetails());
        logger.info("Authorities = " + authentication.getAuthorities());
        logger.info("Principal =" + authentication.getPrincipal());
        logger.info("Class = " + authentication.getClass());

        User user = (User) userService.loadUserByUsername(username);

        //用户不存在
        if (user == null) {
            logger.info("该用户： " + username + " 不存在");
            throw new UsernameNotFoundException("不存在该用户");
        } else if (!passwordEncoder.matches(password,user.getPassword())) { //密码错误
            logger.info("password: " + password);
            logger.info("数据库 password: "+ user.getPassword());
            logger.info("匹配结果: " + passwordEncoder.matches(password,user.getPassword()));
            logger.info("密码错误！");
            throw new BadCredentialsException("密码错误");
        } else{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),authentication.getAuthorities());
            //设置已经验证
            //token.setAuthenticated(true);
            return token;
        }

    }

    /**
     * 询问其是否支持传递给 authenticate()方法
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        //确保authentication能转成该类
        //return aClass.equals(UsernamePasswordAuthenticationToken.class);

        boolean result = true;
        logger.info("supports = " + result);
        //返回false时，即不会再调用authenticate()进行认证操作
        return result;
    }
}
