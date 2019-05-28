package com.ljshuoda.Service;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口："+ UserValidator.class.getSimpleName());
        return user != null;
    }
}
