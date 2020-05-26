package com.jiaxun.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jiaxun.annotation.impl.ArrayNotHasNullValidator;

/**
 * 验证数组元素至少有一个元素
 *
 * @author wangyl
 * @date 2018-7-6
 *
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ArrayNotHasNullValidator.class)
public @interface ArraySizeNotNull {

    String message() default "数组中元素不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
