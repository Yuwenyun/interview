package com.owen.techs.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Aspect(切面): a class that defines point cut and advance
 * JoinPoint(接入点): the method/constructor/attribute that is about to weave the aspect
 * Advance(通知): the behaviour to cut in, there are different types:
 *     before, after, afterReturning, afterThrowing, around
 * PointCut(切入点):
 *
 * 1. by default, spring will use JDK dynamic proxy to create the proxy object
 * 2. when no interface found for the class, will use cglib to create the proxy object
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/aop.xml");
        MyService service = (MyService)context.getBean("myService");
        service.add();
    }
}
