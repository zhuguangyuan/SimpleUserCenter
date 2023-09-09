package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.exceptions.ErrCode;
import com.bruce.auth.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RoleService {
    private final ConcurrentHashMap<String, Role> roleRegistry = new ConcurrentHashMap<>(16);
    private final Set<Role> inUseSet = new HashSet<>();

    public Role createRole(String name) {
        log.info("create role:{}", name);
        if (roleRegistry.get(name) != null) {
            throw new AuthException(ErrCode.ERR_ROLE_ALREADY_EXISTS);
        }

        Role role = Role.builder()
                .name(name)
                .build();
        roleRegistry.putIfAbsent(name, role); // use put may override prev one, so use putIfAbsent

        log.info("create role {} succeed.", name);
        return role;
    }

    public Role delRole(String name) {
        log.info("del role:{}", name);
        if (inUseSet.stream().anyMatch(item -> item.getName().equals(name))) {
            log.info("role {} in use by user. can not delete.", name);
            throw new AuthException(ErrCode.ERR_ROLE_IN_USE);
        }

        Role role = roleRegistry.remove(name);
        if (role == null) {
            throw new AuthException(ErrCode.ERR_ROLE_NOT_EXISTS);
        }

        log.info("del role {} succeed.", name);
        return role;
    }

    public Role getRole(String name) {
        log.info("get role:{}", name);
        return roleRegistry.get(name);
    }

    public boolean setRoleInUse(Role role) {
        return inUseSet.add(role);
    }
}
