package com.bruce.auth.controllers;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.vo.RoleVo;
import com.bruce.auth.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

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
}
