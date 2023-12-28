package com.bruce.others.SpringCreatationDebug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class B implements InitializingBean {
    private String name;

    @Autowired
    private A a;

    @PostConstruct
    public void init() {
        name = "b name";
        log.error(" === PostConstruct-{}", name);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.error(" === 实现 InitializingBean 接口，调用afterPropertiesSet哈数 -{}\n{}\n{}", this.a, this.name, this);
    }

    @AspectAnnotation(name = "waht the helo")
    public void init111() {
        log.warn("这是 b 内的init 111");
    }
}
