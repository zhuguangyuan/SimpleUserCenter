package com.bruce.tom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
public class TomApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomApplication.class, args);
    }

    @GetMapping("jmeter/hello")
    public String hello() throws InterruptedException {
        log.info("线程：{}", Thread.currentThread().getName());
        Thread.sleep(2000L);
        return "Hello bruce.";
    }
}
