package com.benson.mybatis.mapper;

import com.benson.mybatis.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserMapper {

    List<User> getUsers();
}
