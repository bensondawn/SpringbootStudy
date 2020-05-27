package com.jiaxun.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jiaxun.annotation.ArraySizeNotNull;

/**
 * 数组是否有元素校验
 *
 * @author wangyl
 * @date 2018-7-6
 *
 */
public class ArrayNotHasNullValidator implements ConstraintValidator<ArraySizeNotNull, String[]>{

    public void initialize(ArraySizeNotNull arraySizeNotNull) {

    }

    public boolean isValid(String[] arrays, ConstraintValidatorContext constraintValidatorContext) {
        return arrays != null ? (arrays.length > 0) : Boolean.FALSE;
    }

}
