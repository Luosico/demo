package com.luosico.demo;

import org.apache.dubbo.config.annotation.DubboService;
import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/8
 */

@DubboService
//@Component
public class ProviderUserService implements UserService {

    @Override
    public String getName(String name) {
        String result = name + " world";
        System.out.println(result);
        return result;
    }
}
