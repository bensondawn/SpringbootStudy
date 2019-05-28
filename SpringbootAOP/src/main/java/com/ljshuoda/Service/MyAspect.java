package com.ljshuoda.Service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 这是个切面，也就是面向约定编程里的拦截器。
 */
@Aspect
public class MyAspect {

    // 切点
    // 通过正则表达式匹配连接点，printUser方法就是一个连接点。
    @Pointcut("execution(* com.ljshuoda.Service.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before ......");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }


    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println("around before......");
//        jp.proceed();
//        System.out.println("around after......");
    }

}
