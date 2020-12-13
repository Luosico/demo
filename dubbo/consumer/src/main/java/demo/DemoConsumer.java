package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/5
 */
public class DemoConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();

        UserService userService = context.getBean("userService",UserService.class);
        System.out.println(userService.getName("Hello world"));
    }
}
