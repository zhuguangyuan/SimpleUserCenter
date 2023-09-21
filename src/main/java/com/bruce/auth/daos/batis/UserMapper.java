package com.bruce.auth.daos.batis;

import com.bruce.auth.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findByName(String name);

    void save(User user);

    void clearAll(int id);
}
