package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.exceptions.ErrCode;
import com.bruce.auth.models.Role;
import com.bruce.auth.models.TokenInfo;
import com.bruce.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class UserRoleService {
    private final ConcurrentHashMap<String, Set<Role>> userRolesMap = new ConcurrentHashMap<>(16);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    public Set<Role> addRoleToUser(String userName, String roleName) {
        User user = userService.getUser(userName);
        if (user == null) {
            throw new AuthException(ErrCode.ERR_USER_NOT_EXISTS);
        }

        Role role = roleService.getRole(roleName);
        if (role == null) {
            throw new AuthException(ErrCode.ERR_ROLE_NOT_EXISTS);
        }

        // add role to user
        Set<Role> roles;
        synchronized (this.userRolesMap) {
            roles = userRolesMap.get(userName);
            if (roles == null) {
                roles = new HashSet<>();
            }

            roles.add(role);
            userRolesMap.put(userName, roles);
        }

        log.info("addRoleToUser={}, roles={}", user, userRolesMap.get(userName));
        return roles;
    }

    public boolean checkHasRole(String token, String targetRoleName) {
        // check token exists and valid
        TokenInfo tokenInfo = authService.getByCheck(token);
        if (tokenInfo == null) {
            throw new AuthException(ErrCode.ERR_TOKEN_INVALID);
        }

        Set<Role> roles = userRolesMap.get(tokenInfo.getUserName());

        return roles != null && roles.stream()
                .map(Role::getName)
                .anyMatch(item -> item.equals(targetRoleName));
    }

    public List<Role> getAllRoles(String token) {
        // check token exists and valid
        TokenInfo tokenInfo = authService.getByCheck(token);
        if (tokenInfo == null) {
            throw new AuthException(ErrCode.ERR_TOKEN_INVALID);
        }

        // get user from token
        Set<Role> roles = userRolesMap.get(tokenInfo.getUserName());
        if (roles == null || roles.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(roles);
    }
}
