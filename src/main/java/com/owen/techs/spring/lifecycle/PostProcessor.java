package com.owen.techs.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * all the bean definition will be passed to BeanPostProcessor's
 *   1. postProcessBeforeInitialization()
 *   2. postProcessAfterInitialization()
 */
public class PostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("entity")){
            System.out.println("Calling BeanPostProcessor.postProcessBeforeInitialization()...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("entity")){
            System.out.println("Calling BeanPostProcessor.postProcessAfterInitialization()...");
        }
        return bean;
    }
}
