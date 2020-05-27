package com.jiaxun.annotation.impl;

import com.jiaxun.annotation.NotEmptyValidObject;

import javax.validation.*;
import java.util.Set;

/**
 * 对象为空不进行校验，对象非空进行校验
 *
 *  @author wangyl
 *  @date 2018-8-27
 */
public class NotEmptyValidObjectValidator  implements ConstraintValidator<NotEmptyValidObject, Object> {

    private Class clazz;

    public void initialize(NotEmptyValidObject constraintAnnotation) {
        clazz = constraintAnnotation.value();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }else{
            Validator validator = DefaultValidatorFactoryHolder.getValidator();
            Set<ConstraintViolation<Object>> validate = validator.validate(value,clazz);
            if(!validate.isEmpty() && !validate.isEmpty()){
                return false;
            }
            return true;
        }
    }
}
