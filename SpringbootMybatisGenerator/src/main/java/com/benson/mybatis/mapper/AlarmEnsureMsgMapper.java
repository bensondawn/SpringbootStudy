package com.benson.mybatis.mapper;

import com.benson.mybatis.entity.AlarmEnsureMsg;

public interface AlarmEnsureMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmEnsureMsg record);

    int insertSelective(AlarmEnsureMsg record);

    AlarmEnsureMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmEnsureMsg record);

    int updateByPrimaryKey(AlarmEnsureMsg record);
}