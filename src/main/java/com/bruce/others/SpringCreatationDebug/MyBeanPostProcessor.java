package com.bruce.others.SpringCreatationDebug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {
    private String name;

    @PostConstruct
    public void init() {
        name = "d name";
        log.error(" === PostConstruct-{}", name);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(" === BeanPostProcessor postProcessBeforeInitialization-{}", beanName);
        return bean;
    }

    @Nullable
     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(" === BeanPostProcessor postProcessAfterInitialization-{}", beanName);
        return bean;
    }

}
