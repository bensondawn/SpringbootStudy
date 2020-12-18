package com.jiaxun.aspect;

import com.jiaxun.annotation.MyFirstAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyFirstAspect {

    @Pointcut("@annotation(com.jiaxun.annotation.MyFirstAnnotation)" )
    public void addAdvice(){}

    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint joinPoint){
        System.out.println("通知开始");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("通知结束" + result);

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String deviceId = (String) args[0];
            if (!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }

//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
        return result;
    }

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
        System.out.println("打印：" + annotation.value() + " before方法执行");
    }

    @After("addAdvice()")
    public void after() {
        System.out.println("打印：" + "after方法执行");
    }
}