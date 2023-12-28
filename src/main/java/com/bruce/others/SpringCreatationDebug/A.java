package com.bruce.others.SpringCreatationDebug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class A {
    private String name;

    @Lazy
    @Autowired
    private B b;

    @PostConstruct
    public void init() {
        name = "a name";
        System.out.println("post construct init a: " + this);
    }
}
