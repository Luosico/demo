package com.luosico.springcloudprovider.demo;

import com.luosico.UserService;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * @Author: luo kai fa
 * @Date: 2020/12/25
 */
@DubboService
public class ProviderUserService implements UserService {
    @Override
    public String hello(String str) {
        str = str + "world";
        System.out.println(str);
        return str;
    }
}
