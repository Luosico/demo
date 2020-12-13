package com.luosico;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: luo kai fa
 * @Date: 2020/12/4
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.luosico.demo")
public class SpringbootProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class, args);
    }
}
