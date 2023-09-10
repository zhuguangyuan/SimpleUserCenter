package com.bruce.auth.controllers;

import com.bruce.auth.models.User;
import com.bruce.auth.models.vo.UserVo;
import com.bruce.auth.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public UserVo createUser(@RequestBody Map<String, String> params) {
//        should not log user's password
//        log.info("createUser req:{}", params);
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
}
