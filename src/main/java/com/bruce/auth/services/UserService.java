package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.exceptions.ErrCode;
import com.bruce.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserService {
    private final ConcurrentHashMap<String, User> userRegistry = new ConcurrentHashMap<>(16);

    public User createUser(String name, String password) {
        log.info("create user:{}", name);
        if (userRegistry.get(name) != null) {
            throw new AuthException(ErrCode.ERR_USER_ALREADY_EXISTS);
        }

        User user = User.build(name, password);
        User old = userRegistry.putIfAbsent(name, user); // use put may override prev one, so use putIfAbsent
        if (old != null) {
            throw new AuthException(ErrCode.ERR_USER_ALREADY_EXISTS);
        }

        log.info("create user {} succeed.", name);
        return user;
    }

    public User delUser(String name) {
        log.info("del user:{}", name);

        User user = userRegistry.remove(name);
        if (user == null) {
            throw new AuthException(ErrCode.ERR_USER_NOT_EXISTS);
        }

        log.info("del user {} succeed.", name);
        return user;
    }

    /**
     * get user with password
     * @param name name of user
     * @param password password of user
     * @return return user if user exists, and password is correct,
     * else return null
     */
    public User getWithPassword(String name, String password) {
        User user = userRegistry.get(name);
        if (user == null || !user.checkPassCorrect(password)) {
            return null;
        }

        return user;
    }

    public User getUser(String name) {
        log.info("get user:{}", name);
        return userRegistry.get(name);
    }
}
