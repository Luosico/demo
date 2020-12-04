package com.luosico.demo;


import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/8
 */

@Controller
public class DemoController {


    @DubboReference
    UserService userService;    //生成Dubbo调用服务提供者对象

    //日志对象
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "/userService")
    @ResponseBody
    public String getUserService() {
        String hello = "hello";
        String result = userService.getName(hello);
        System.out.println(result);
        logger.info("Message is : " + result);
        logger.info(String.valueOf(RpcContext.getContext().getRemoteAddress()));
        logger.info(String.valueOf(RpcContext.getContext().getLocalAddress()));
        return result;
    }

    @RequestMapping("/init")
    @ResponseBody
    public String init() {

        return "hello";
    }
}
