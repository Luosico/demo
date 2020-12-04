package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/5
 */
public class MainApplication {
    public static void main(String[] args) throws IOException {
        //指定服务暴露的配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        //启动spring容器并暴露服务
        context.start();
        //读取一个byte ,起到阻塞的作用，不让程序执行完成
        //按任意键退出
        System.in.read();
    }
}
