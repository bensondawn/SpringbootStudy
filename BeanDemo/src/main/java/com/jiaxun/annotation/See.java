package com.jiaxun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 标记，提高代码可读性，无实际意义
 * @author wangyl
 * @date 2018-06-08
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
public @interface See {

	String value() default "";
}
