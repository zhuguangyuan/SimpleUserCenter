package com.bruce.auth.daos.batis;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> findByName(String name);

    void save(Role role);

    void clearAll(int id);
}
