package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/5
 */
@Service
public class DemoConsumer {

    @Autowired
    UserService userService;

    public String getUser(){
        return userService.getName("Hello world");
    }

    public static void main(String[] args) {
        DemoConsumer demoConsumer = new DemoConsumer();
        System.out.println(demoConsumer.getUser());
    }
}
