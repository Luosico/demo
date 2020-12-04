package com.luosico.springsecurityimagecheck.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/17
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/")
    public String toLogin() {

        return "redirect:/login.html";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        //logger.info("进入Login.html");
        return "login";
    }

    @GetMapping(value = "/hello.html")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/main.html")
    public String loginCheck() {
        //logger.info("进入main.html");
        return "main";
    }

    @GetMapping("/error.html")
    public String toError() {
        return "error";
    }

    @GetMapping("/invalidSession")
    @ResponseBody
    public String invalidSession(){
        return "Session 已失效，请重新登录";
    }

}
