package com.bruce.auth.controllers;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.vo.RoleRspVo;
import com.bruce.auth.models.vo.UserRoleReq4AddVo;
import com.bruce.auth.services.UserRoleService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/user/role/add")
    public Boolean addRoleToUser(@RequestBody UserRoleReq4AddVo req) {
        log.info("addRoleToUser req:{}", req);
        String userName = req.getUserName();
        String roleName = req.getRoleName();
        userRoleService.addRoleToUser(userName, roleName);
        return true;
    }

    @GetMapping("/user/role/check")
    public Boolean checkRole(@ApiParam(required = true, example = "myToken") @RequestParam String token,
                             @ApiParam(required = true, example = "role1") @RequestParam String roleName) {
        log.info("checkRole req, token:{}, roleName:{}", token, roleName);
        return userRoleService.checkHasRole(token, roleName);
    }

    @GetMapping("/user/role/all")
    public List<RoleRspVo> getAllRoles(@ApiParam(required = true, example = "myToken") @RequestParam String token) {
        log.info("getAllRoles req, token:{}", token);
        List<Role> roles = userRoleService.getAllRoles(token);
        return roles.stream()
                .map(item -> RoleRspVo.builder().name(item.getName()).build())
                .collect(Collectors.toList());
    }
}
