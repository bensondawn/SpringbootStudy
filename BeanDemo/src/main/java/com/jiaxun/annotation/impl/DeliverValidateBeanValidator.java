package com.jiaxun.annotation.impl;

import com.jiaxun.annotation.DeliverValidateBean;

import javax.validation.*;
import java.util.Objects;
import java.util.Set;

/**
 *  验证POJO对象中的引用对象的验证的具体实现
 *
 * @author wangyl
 * @date 2018-7-16
 *
 */
public class DeliverValidateBeanValidator implements ConstraintValidator<DeliverValidateBean, Object> {

    /**
     * 分组验证class对象
     */
    private Class clazz;

    public void initialize(DeliverValidateBean deliverValidateBean) {
        clazz = deliverValidateBean.value();
    }

    public boolean isValid(Object bean, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(clazz)){
            return false;
        }
        Validator validator = DefaultValidatorFactoryHolder.getValidator();
        Set<ConstraintViolation<Object>> validate = validator.validate(bean,clazz);
        if(!validate.isEmpty() && Objects.nonNull(validate.iterator().next())){
            return false;
        }
        return true;
    }

}