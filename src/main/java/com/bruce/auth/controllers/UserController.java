package com.bruce.auth.controllers;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.User;
import com.bruce.auth.models.vo.RoleVo;
import com.bruce.auth.models.vo.UserVo;
import com.bruce.auth.services.RoleService;
import com.bruce.auth.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/create")
    public UserVo createUser(@RequestBody Map<String, String> params) {
        log.info("createUser req:{}", params);
        String userName = params.get("name");
        String password = params.get("password");
        User user = userService.createUser(userName, password);
        return UserVo.builder()
                .name(user.getName())
                .build();
    }

    @PostMapping("/user/del")
    public UserVo delUser(@RequestBody Map<String, String> params) {
        log.info("delUser req:{}", params);
        String userName = params.get("name");
        User user = userService.delUser(userName);
        return UserVo.builder()
                .name(user.getName())
                .build();
    }

    @PostMapping("/role/create")
    public RoleVo createRole(@RequestBody Map<String, String> params) {
        log.info("createRole req:{}", params);
        String roleName = params.get("name");
        Role role = roleService.createRole(roleName);
        return RoleVo.builder()
                .name(role.getName())
                .build();
    }

    @PostMapping("/role/del")
    public RoleVo delRole(@RequestBody Map<String, String> params) {
        log.info("delRole req:{}", params);
        String roleName = params.get("name");
        Role role = roleService.delRole(roleName);
        return RoleVo.builder()
                .name(role.getName())
                .build();
    }

    @PostMapping("/user/auth/login")
    public String login(@RequestBody Map<String, String> params) {
        log.info("login req:{}", params);

        return null;
    }

    @PostMapping("/user/auth/logout")
    public Boolean logout(@RequestBody Map<String, String> params) {
        log.info("logout req:{}", params);

        return false;
    }

    @PostMapping("/user/role/add")
    public Boolean addRoleToUser(@RequestBody Map<String, String> params) {
        log.info("addRoleToUser req:{}", params);

        return false;
    }

    @GetMapping("/user/role/check")
    public Boolean checkRole(@RequestParam String token, @RequestParam String roleName) {
        log.info("checkRole req, token:{}, roleName:{}", token, roleName);

        return false;
    }

    @GetMapping("/user/role/all")
    public List<RoleVo> getAllRoles(@RequestParam String token) {
        log.info("getAllRoles req, token:{}", token);

        return null;
    }
}
