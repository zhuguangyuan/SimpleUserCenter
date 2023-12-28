package com.bruce.others;

import org.junit.jupiter.api.Test;

import javax.annotation.PostConstruct;

public class HibernateUsageTest {
    @Test
    public void usage1() {

    }

    static class BeanInstance1 {
        private String name;

        @PostConstruct
        public void init() {
            System.out.println("这是 postConstruct");
        }
    }
}
