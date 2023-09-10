package com.bruce.auth.controllers;

import com.bruce.auth.models.vo.UserReq4LoginVo;
import com.bruce.auth.models.vo.UserReq4LogoutVo;
import com.bruce.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/user/auth/login")
    public String login(@RequestBody UserReq4LoginVo req) {
//        should not log user's password
//        log.info("login req:{}", req);
        String name = req.getName();
        String password = req.getPassword();
        return authService.login(name, password);
    }

    @PostMapping("/user/auth/logout")
    public Boolean logout(@RequestBody UserReq4LogoutVo req) {
        log.info("logout req:{}", req);
        String token = req.getToken();
        authService.logout(token);
        return true;
    }
}
