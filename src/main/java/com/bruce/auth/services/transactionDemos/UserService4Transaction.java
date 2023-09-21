package com.bruce.auth.services.transactionDemos;

import com.bruce.auth.daos.batis.UserMapper;
import com.bruce.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService4Transaction {
    @Autowired
    private UserMapper mapper;

    public void persist(User user, boolean exp) {
        log.info("to persist: {}", user);
        this.mapper.save(user);
        List<User> result = this.mapper.findByName(user.getName());
        log.info("persist result: {}", result);
        if (exp) {
            throw new RuntimeException("exp in outer. when persist user.");
        }
    }

    public List<User> query(String name) {
        List<User> result = this.mapper.findByName(name);
        log.info("query user result: {}", result);
        return result;
    }

    public void clearAll(int id) {
        this.mapper.clearAll(id);
    }
}
