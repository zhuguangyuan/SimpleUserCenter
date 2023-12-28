package com.bruce.others.SpringCreatationDebug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
@Slf4j
public class App implements ApplicationContextAware {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("打印出来了 ={}", applicationContext.getBean("b"));
        B b = (B) applicationContext.getBean("b");
        b.init111(); // 这个才生效。因为这个才能利用Spring的代理
        this.init111(); // 注意这个是不生效的。这是类内部的调用，没有经过Spring的代理 所以注解根本不起作用
    }

    @AspectAnnotation(name = "waht the helo")
    public void init111() {
        log.warn("这是C内的init 111, 是类内调用，注解不生效，是走不到切面的");
    }
}
