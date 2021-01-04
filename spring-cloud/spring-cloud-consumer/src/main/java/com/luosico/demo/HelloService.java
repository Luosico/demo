package com.luosico.demo;

import com.luosico.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: luo kai fa
 * @Date: 2020/12/28
 */

@DubboService
public class HelloService implements UserService {
    @Override
    public String hello(String str) {
        return "Hello world";
    }
}
