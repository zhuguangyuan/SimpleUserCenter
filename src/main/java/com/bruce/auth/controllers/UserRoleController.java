package com.bruce.auth.controllers;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.vo.RoleVo;
import com.bruce.auth.services.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/user/role/add")
    public Boolean addRoleToUser(@RequestBody Map<String, String> params) {
        log.info("addRoleToUser req:{}", params);
        String userName = params.get("user_name");
        String roleName = params.get("role_name");
        userRoleService.addRoleToUser(userName, roleName);
        return true;
    }

    @GetMapping("/user/role/check")
    public Boolean checkRole(@RequestParam String token, @RequestParam String roleName) {
        log.info("checkRole req, token:{}, roleName:{}", token, roleName);
        return userRoleService.checkHasRole(token, roleName);
    }

    @GetMapping("/user/role/all")
    public List<RoleVo> getAllRoles(@RequestParam String token) {
        log.info("getAllRoles req, token:{}", token);
        List<Role> roles = userRoleService.getAllRoles(token);
        return roles.stream()
                .map(item -> RoleVo.builder().name(item.getName()).build())
                .collect(Collectors.toList());
    }
}
