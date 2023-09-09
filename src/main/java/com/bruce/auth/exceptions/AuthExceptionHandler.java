package com.bruce.auth.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public String handleException(AuthException e) {
        log.info("AuthException catch in AuthExceptionHandler, e:{}", e.getMessage());
        return e.printDesc();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        log.info("Exception catch in AuthExceptionHandler, e", e);
        return e.getMessage();
    }
}
