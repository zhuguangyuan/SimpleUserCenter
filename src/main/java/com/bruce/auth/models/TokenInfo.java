package com.bruce.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class TokenInfo {
    private String userName;
    private String token;
    private long expireTime;

    public static TokenInfo generateNew(String userName, long ttl) {
        return TokenInfo.builder()
                .token(tokenGenerate())
                .userName(userName)
                .expireTime(System.currentTimeMillis() + ttl)
                .build();
    }

    private static String tokenGenerate() {
        return UUID.randomUUID().toString();
    }

    public boolean valid() {
        return this.expireTime > System.currentTimeMillis();
    }
}

