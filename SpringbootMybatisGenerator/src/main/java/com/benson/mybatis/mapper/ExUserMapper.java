package com.benson.mybatis.mapper;

import com.benson.mybatis.entity.User;
import com.benson.mybatis.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

// 因为generator自动生成器每次都会覆盖之前生成的User.java、UserExample、UserMapper.java、UserMapper.xml，
// 但是我后期自己定义的方法会被全部覆盖掉，所以为了防止自定义的方法被覆盖，对应的定义了Ex文件，这样自定义的方法
// 都放在Ex文件中，但是要跟Ex.xml文件中的namespace对应起来。
// 详见https://blog.csdn.net/u010696826/article/details/86306113
public interface ExUserMapper {
    List<User> getUsers();
}