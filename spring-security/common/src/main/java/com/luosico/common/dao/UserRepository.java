package com.luosico.common.dao;

import com.luosico.common.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @Author: luo kai fa
 * @Date: 2020/11/18
 */

@Repository
public class UserRepository {

    HashMap<String, User> users;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRepository() {
        User u1 = new User("u1", new BCryptPasswordEncoder().encode("123"));
        User u2 = new User("u2", "456");

        users = new HashMap<>();
        users.put("u1", u1);
        users.put("u2", u2);
    }

    public User findUserByUsername(String username) {
        logger.info("是否存在用户: " + users.containsKey(username));
        if (users.containsKey(username)) {
            logger.info("username: " + username);
            logger.info(String.valueOf(users.get(username) == null));
            return users.get(username);
        }
        //返回空对象会报 error 错误
        return new User(null, null);
    }

}
