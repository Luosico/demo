package demo;

import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/5
 */
public class UserServiceImpl implements UserService {

    public String getName(String name) {
        System.out.println(name);
        return name;
    }
}
