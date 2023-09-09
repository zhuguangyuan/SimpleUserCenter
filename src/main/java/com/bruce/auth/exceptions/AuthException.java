package com.bruce.auth.exceptions;

public class AuthException extends RuntimeException {
    private int code;

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(ErrCode code) {
        super(code.getDescription());
        this.code = code.getValue();
    }

    public String printDesc() {
        return code + ": " + this.getMessage();
    }
}
