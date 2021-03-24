package com.luosico.demo;


import com.luosico.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author: luo kai fa
 * @Date: 2020/11/8
 */

@Controller
@RefreshScope
public class DemoController {


    @DubboReference
    UserService userService;    //生成Dubbo调用服务提供者对象

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //日志对象
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Value("${info:default value}")
    private String info;

    @RequestMapping(value = "/userService")
    @ResponseBody
    public String getUserService() {
        String hello = "hello";
        String result = userService.hello(hello);
        System.out.println(result);
        logger.info("Message is : " + result);
        //logger.info(String.valueOf(RpcContext.getContext().getRemoteAddress()));
        //logger.info(String.valueOf(RpcContext.getContext().getLocalAddress()));
        return result;
    }

    @RequestMapping("/init")
    @ResponseBody
    public String init() {

        return "hello";
    }

    @GetMapping("/config")
    @ResponseBody
    public String nacosConfig(){
        return "info: " + info;
    }

    @GetMapping("/redis")
    @ResponseBody
    public String testRedisConfig(){
        redisTemplate.opsForValue().set("redis","success");
        return "try";
    }
}
