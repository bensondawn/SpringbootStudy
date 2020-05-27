package com.jiaxun.annotation;


import com.jiaxun.annotation.impl.DeliverValidateBeanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *  验证POJO对象中的引用对象的验证
 *
 * @author wangyl
 * @date 2018-7-16
 *
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DeliverValidateBeanValidator.class)
public @interface DeliverValidateBean {

    Class<?> value();

    String message() default "组合对象中对象校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
