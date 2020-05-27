package com.jiaxun.annotation.impl;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class DefaultValidatorFactoryHolder {
	
	private static ValidatorFactory defaultValidatorFactory = Validation.buildDefaultValidatorFactory();
	
	public static Validator getValidator() {
		return defaultValidatorFactory.getValidator();
	}

}
