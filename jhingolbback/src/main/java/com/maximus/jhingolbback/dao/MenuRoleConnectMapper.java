package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.MenuRoleConnect;
import com.maximus.jhingolbback.model.MenuRoleConnectExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuRoleConnectMapper {
    List<MenuRoleConnect> selectByExample(MenuRoleConnectExample example);
}