package com.jiaxun.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jiaxun.annotation.impl.ListEmptyAndNotEmptyValidator;

/**
 * 集合为空不进行验证，集合非空进行POJO对象验证
 *
 * @author wangyl
 * @date 2018-7-16
 *
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ListEmptyAndNotEmptyValidator.class)
public @interface ListEmptyAndNotEmpty {

	Class<?> value();

    String message() default "集合中元素校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
