package com.bruce.others.SpringCreatationDebug;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class MyAspect {
    @PostConstruct
    public void init() {
        log.warn("初始化。。。111");
    }

    @Pointcut("@annotation(com.bruce.others.SpringCreatationDebug.AspectAnnotation)")
    public void whattheh() {

    }

    @Before("whattheh()")
    public void dd(JoinPoint joinPoint) {
        log.error("这是执行方法前 方法名 {}", joinPoint.getSignature().getName());
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        log.error("这是执行方法 注解 {}", method.getAnnotation(AspectAnnotation.class).name());
    }

    @After("whattheh()")
    public void dd1(JoinPoint joinPoint) {
        log.error("这是执行方法hou 方法名 {}", joinPoint.getSignature().getName());
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        log.error("这是执行方法hou 注解 {}", method.getAnnotation(AspectAnnotation.class).name());
    }
}
