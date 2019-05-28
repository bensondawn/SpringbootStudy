package com.ljshuoda.Service;

import org.springframework.stereotype.Service;

// 目标对象，即被代理对象。
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void printUser() {
    }

    // 连接点
    @Override
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("检查用户参数是否为空......");
        }
        System.out.print("id = " + user.getId());
        System.out.print("\nusername = " + user.getUsername());
        System.out.println("\nnote = " + user.getNote());
    }

    @Override
    public void manyAspects() {

    }
}
