package com.luosico.common.config;

import com.luosico.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/17
 */
@Configuration
@EnableWebSecurity //启用方法安全设置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserAuthenticationProvider UserAuthenticationProvider;

    @Autowired
    UserService userService;

    @Autowired
    AfterProvider afterProvider;

    @Autowired
    SuccessHandle successHandle;

    @Autowired
    FailureHandle failureHandle;

    /**
     * 设置加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 通过重载，配置user-detail服务
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //设置自定义认证
        //auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

        //自定义 AuthenticationProvider
        auth.authenticationProvider(UserAuthenticationProvider);
        //另一个 provider，只要有一个provider验证成功就算成功
        //auth.authenticationProvider(afterProvider);

        //存在内存中
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("hello").password(new BCryptPasswordEncoder().encode("123")).roles("USER");

    }

    /**
     * 通过重载，配置Spring Security的Filter链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 通过重载，配置如何拦截器保护请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP basic
                //.httpBasic()
                .authorizeRequests() //授权配置
                //这些路径不需要身份认证
                .antMatchers("/login.html", "/error.html").permitAll()
                //其他路径需要身份认证
                .anyRequest().authenticated()
                .and()
                //loginPage指定URL来自定义登录界面
                .formLogin()//登录表单
                .loginPage("/login.html") //登录页面 URL
                .loginProcessingUrl("/login") //登录信息提交url
                .defaultSuccessUrl("/main.html")
                .permitAll()//登录成功后有权限访问所有界面
                .successHandler(successHandle)
                .failureHandler(failureHandle)
                .and()
                .logout()
                .logoutUrl("/logout");
        //关闭csrf跨域攻击防御
        //如果不关闭，需要在请求接口的时候加入csrfToken才行
        http.csrf().disable();

    }

}
