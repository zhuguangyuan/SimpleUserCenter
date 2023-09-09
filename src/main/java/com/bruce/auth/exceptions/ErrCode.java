package com.bruce.auth.exceptions;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ErrCode {
    ERR_NO(0, "success"),
    ERR_INTERVAL_EXP(1000000, "internal error."),
    ERR_PARAMS_INVALID(1000001, "input params invalid, please check again."),
    // user err
    ERR_USER_ALREADY_EXISTS(1001001, "user already exists"),
    ERR_USER_NOT_EXISTS(1001003, "user not exists"),
    ERR_USER_PASSWORD_NOT_MATCH(1001005, "user and password not match, may be not registry or password incorrect."),

    // role err
    ERR_ROLE_ALREADY_EXISTS(1001011, "role already exists"),
    ERR_ROLE_NOT_EXISTS(1001013, "role not exists"),
    ;

    private final int value;
    private final String description;

    ErrCode(int v, String d) {
        this.value = v;
        this.description = d;
    }

    public static ErrCode of(int v) {
        return Stream.of(ErrCode.values())
                .filter(c -> c.getValue() == v)
                .findFirst()
                .orElse(null);
    }
}
