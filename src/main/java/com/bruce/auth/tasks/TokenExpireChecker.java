package com.bruce.auth.tasks;

import com.bruce.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * if more than one task here
 * it will be better to implement SchedulingConfigurer to specify thread pool
 */
@Component
@EnableScheduling
@Slf4j
public class TokenExpireChecker {
    @Autowired
    private AuthService authService;

    // check every 5 min
    @Scheduled(cron = "${user.token.checkInterval}")
    public void checkToken() {
        log.info("checkToken timely");
        authService.removeExpireToken();
    }
}
