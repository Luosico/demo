package com.luosico.springsecurityimagecheck.service;

import com.luosico.springsecurityimagecheck.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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

    //解密
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);


    /**
     * 对信息进行认证
     *
     * @param authentication 未认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的用户名
        String username = authentication.getName();
        //获取输入的明文
        String password = passwordEncoder.encode((String) authentication.getCredentials());
        System.out.println(username);

        logger.info("username = " + username);
        logger.info("password = " + password);

        User user = (User) userService.loadUserByUsername(username);

        //用户不存在
        if (user == null) {
            logger.info("该用户： " + username + " 不存在");
            throw new UsernameNotFoundException("不存在该用户");
        /*} else if (!passwordEncoder.matches(password,user.getPassword())) { //密码错误
            logger.info("密码错误！");
            throw new BadCredentialsException("密码错误");*/
        } else
            return new UsernamePasswordAuthenticationToken(username, password);

    }

    /**
     * 询问其是否支持传递给authenticate()方法
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        //确保authentication能转成该类
        //return aClass.equals(UsernamePasswordAuthenticationToken.class);
        System.out.println("-----------------------");

        //返回false时，即不会再调用authenticate()进行认证操作
        return false;
    }
}
