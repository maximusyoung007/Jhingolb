package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.AuthUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthUserMapper {
    List<AuthUser> selectByExample(AuthUserExample example);
}