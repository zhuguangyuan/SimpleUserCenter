package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.exceptions.ErrCode;
import com.bruce.auth.models.TokenInfo;
import com.bruce.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class AuthService {
    private final ConcurrentHashMap<String, TokenInfo> tokenMap = new ConcurrentHashMap<>(16);
    private final PriorityQueue<TokenInfo> tokenExpireTimer = new PriorityQueue<>(Comparator.comparingLong(TokenInfo::getExpireTime));

    @Value("${user.token.ttl:10000}")
    private String tokenTTL;

    @Autowired
    private UserService userService;

    public String login(String name, String pass) {
        log.info("user login:{}", name);
        User user = userService.getWithPassword(name, pass);
        if (user == null) {
            throw new AuthException(ErrCode.ERR_USER_PASSWORD_NOT_MATCH);
        }
        TokenInfo tokenInfo = TokenInfo.generateNew(user.getName(), this.getTtl());
        tokenMap.put(tokenInfo.getToken(), tokenInfo);
        tokenExpireTimer.offer(tokenInfo); // timeout will remove

        log.info("user login succeed:{}, tokenInfo:{}", name, tokenInfo);
        return tokenInfo.getToken();
    }

    public void logout(String token) {
        log.info("user logout, token:{}", token);

        if (token == null) {
            return;
        }
        tokenMap.remove(token);

        log.info("user logout succeed, token:{}", token);
    }

    public TokenInfo getByCheck(String token) {
        log.info("get token by check, token:{}, map:{}", token, tokenMap.entrySet());

        if (token == null) {
            return null;
        }
        TokenInfo tokenInfo = tokenMap.get(token);
        if (tokenInfo == null) {
            return null;
        }
        if (!tokenInfo.valid()) {
            tokenMap.remove(tokenInfo.getToken());
            return null;
        }

        log.info("get token by check succeed, tokenInfo:{}", tokenInfo);
        return tokenInfo;
    }

    private long getTtl() {
        return Long.parseLong(tokenTTL);
    }

    public void removeExpireToken() {
        log.info("removeExpireToken begin");
        while (!tokenExpireTimer.isEmpty()
                && tokenExpireTimer.peek().getExpireTime() < System.currentTimeMillis()) {
            TokenInfo expireToken = tokenExpireTimer.poll();
            if (expireToken != null) {
                log.info("token expire, will remove:{}", expireToken);
                tokenMap.remove(expireToken.getToken());
            }
        }
        log.info("removeExpireToken end");
    }
}
