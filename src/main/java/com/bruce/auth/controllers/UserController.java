package com.bruce.auth.controllers;

import com.bruce.auth.models.User;
import com.bruce.auth.models.vo.UserReq4CreateVo;
import com.bruce.auth.models.vo.UserReq4DelVo;
import com.bruce.auth.models.vo.UserRspVo;
import com.bruce.auth.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public UserRspVo createUser(@RequestBody UserReq4CreateVo req) {
//        should not log user's password
//        log.info("createUser req:{}", req);
        String userName = req.getName();
        String password = req.getPassword();
        User user = userService.createUser(userName, password);
        return UserRspVo.builder()
                .name(user.getName())
                .build();
    }

    @PostMapping("/user/del")
    public UserRspVo delUser(@RequestBody UserReq4DelVo req) {
        log.info("delUser req:{}", req);
        String userName = req.getName();
        User user = userService.delUser(userName);
        return UserRspVo.builder()
                .name(user.getName())
                .build();
    }
}
