package com.bruce.auth.controllers;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.vo.*;
import com.bruce.auth.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role/create")
    public RoleRspVo createRole(@RequestBody RoleReq4CreateVo req) {
        log.info("createRole req:{}", req);
        Role role = roleService.createRole(req.getName());
        return RoleRspVo.builder()
                .name(role.getName())
                .build();
    }

    @PostMapping("/role/del")
    public RoleRspVo delRole(@RequestBody RoleReq4DelVo req) {
        log.info("delRole req:{}", req);
        Role role = roleService.delRole(req.getName());
        return RoleRspVo.builder()
                .name(role.getName())
                .build();
    }
}
