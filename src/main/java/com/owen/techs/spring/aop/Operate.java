package com.owen.techs.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Operate {

    /**
     * execution: weave the advance when calling method
     * *: any return type
     * ..: sub-package
     * *: any class
     * *: any method
     */
    @Pointcut("execution(* com.owen.techs.spring.aop..*.*(..))")
    public void anyMethod(){}

    @Before("anyMethod()")
    public void doBefore(){
        System.out.println("AOP Before Advice...");
    }

    @After("anyMethod()")
    public void doAfter(){
        System.out.println("AOP After Advice...");
    }

    @AfterReturning(pointcut="anyMethod()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal){
        System.out.println("AOP AfterReturning Advice:" + returnVal);
    }

    @AfterThrowing(pointcut="anyMethod()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("AOP AfterThrowing Advice..." + error);
        System.out.println("AfterThrowing...");
    }

    @Around("anyMethod()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("AOP Aronud before...");
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("AOP Aronud after...");
    }
}
