package com.bruce.auth.controllers;

import com.bruce.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/user/auth/login")
    public String login(@RequestBody Map<String, String> params) {
        log.info("login req:{}", params);
        String name = params.get("name");
        String password = params.get("password");
        return authService.login(name, password);
    }

    @PostMapping("/user/auth/logout")
    public Boolean logout(@RequestBody Map<String, String> params) {
        log.info("logout req:{}", params);
        String token = params.get("token");
        authService.logout(token);
        return true;
    }
}
