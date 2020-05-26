package com.jiaxun.annotation.impl;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.jiaxun.annotation.ListEmptyAndNotEmpty;

/**
 * 集合为空不进行验证，集合非空进行POJO对象验证
 *
 * @author wangyl
 * @date 2018-7-16
 *
 */
public class ListEmptyAndNotEmptyValidator implements ConstraintValidator<ListEmptyAndNotEmpty, List<?>>{

	/**
	 * 分组验证class对象
	 */
	private Class clazz;

	public void initialize(ListEmptyAndNotEmpty constraintAnnotation) {
		clazz = constraintAnnotation.value();
	}

	public boolean isValid(List<?> value, ConstraintValidatorContext context) {
		if(Objects.isNull(clazz)){
			return false;
		}
		boolean isEmpty = Objects.isNull(value) || value.isEmpty() || (value.size() == 0);
		if(isEmpty){
			return true;
		}else{
			Validator validator = DefaultValidatorFactoryHolder.getValidator();
			for(Object v : value){
				Set<ConstraintViolation<Object>> validate = validator.validate(v,clazz);
				if(!validate.isEmpty() && Objects.nonNull(validate.iterator().next())){
					return false;
				}
			}
			return true;
		}
	}
}
