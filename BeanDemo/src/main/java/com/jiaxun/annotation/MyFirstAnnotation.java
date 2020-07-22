package com.jiaxun.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
//注解的生命周期，表示注解会被保留到什么阶段，可以选择编译阶段、类加载阶段，或运行阶段
@Retention(RetentionPolicy.RUNTIME)
//注解作用的位置，ElementType.METHOD表示该注解仅能作用于方法上
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyFirstAnnotation {
    @AliasFor("cron")
    String value() default "";

    @AliasFor("value")
    String cron() default "";

    String name() default "";
}