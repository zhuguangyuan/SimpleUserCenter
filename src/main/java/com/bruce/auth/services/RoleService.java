package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.exceptions.ErrCode;
import com.bruce.auth.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RoleService {
    private final ConcurrentHashMap<String, Role> roleRegistry = new ConcurrentHashMap<>(16);

    public Role createRole(String name) {
        log.info("create role:{}", name);
        if (name == null) {
            throw new AuthException(ErrCode.ERR_PARAMS_INVALID);
        }
        if (roleRegistry.get(name) != null) {
            throw new AuthException(ErrCode.ERR_ROLE_ALREADY_EXISTS);
        }

        Role role = Role.builder()
                .name(name)
                .build();
        roleRegistry.put(name, role);

        log.info("create role {} succeed.", name);
        return role;
    }

    public Role delRole(String name) {
        log.info("del role:{}", name);
        if (name == null) {
            throw new AuthException(ErrCode.ERR_PARAMS_INVALID);
        }

        Role role = roleRegistry.remove(name);
        if (role == null) {
            throw new AuthException(ErrCode.ERR_ROLE_NOT_EXISTS);
        }

        log.info("del role {} succeed.", name);
        return role;
    }
}
