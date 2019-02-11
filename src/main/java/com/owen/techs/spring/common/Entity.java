package com.owen.techs.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * Aware interfaces are used to make the bean to be are of a certain attributes
 * eg: BeanNameAware.setBeanName() will pass the id of bean configured in xml to the bean for use
 */
public class Entity implements InitializingBean, BeanNameAware, BeanFactoryAware {

    private String name;
    private int age;

    public Entity(){
        System.out.println("Calling Entity.java's constructor...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting entity's name to " + name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Setting entity's age to " + age);
        this.age = age;
    }

    public void init(){
        System.out.println("Calling entity's init()...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Calling InitializingBean.afterPropertiesSet()...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Calling BeanFactoryAware.setBeanFactory()...");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("Calling BeanNameAware.setBeanName()...");
    }
}
