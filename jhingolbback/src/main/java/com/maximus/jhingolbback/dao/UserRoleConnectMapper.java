package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.UserRoleConnect;
import com.maximus.jhingolbback.model.UserRoleConnectExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserRoleConnectMapper {
    List<UserRoleConnect> selectByExample(UserRoleConnectExample example);
}