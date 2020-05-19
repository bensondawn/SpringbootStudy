package com.benson.mybatis.service;

import com.benson.mybatis.entity.User;
import com.benson.mybatis.entity.UserExample;
import com.benson.mybatis.mapper.ExUserMapper;
import com.benson.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExUserMapper exUserMapper;

    public long getUserCount(){
        UserExample userExample = new UserExample();
        return userMapper.countByExample(userExample);
    }

    public List<User> getUsers(){
        return exUserMapper.getUsers();
    }
}
