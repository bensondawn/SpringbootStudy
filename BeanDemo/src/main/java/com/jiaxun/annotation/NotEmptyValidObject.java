package com.jiaxun.annotation;

import com.jiaxun.annotation.impl.NotEmptyValidObjectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 对象为空不进行校验，对象非空进行校验
 *
 *  @author wangyl
 *  @date 2018-8-27
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotEmptyValidObjectValidator.class)
public @interface NotEmptyValidObject {

    Class<?> value();

    String message() default "非空对象校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
