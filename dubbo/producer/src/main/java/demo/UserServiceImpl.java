package demo;

import service.UserService;

/**
 * @Author: luo kai fa
 * @Date: 2020/12/13
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getName(String name) {
        System.out.println(name);
        return name;
    }
}
