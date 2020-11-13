package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.MenuRoleConnect;
import com.maximus.jhingolbback.model.MenuRoleConnectExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuRoleConnectMapper {
    long countByExample(MenuRoleConnectExample example);

    int deleteByExample(MenuRoleConnectExample example);

    int deleteByPrimaryKey(String id);

    int insert(MenuRoleConnect record);

    int insertSelective(MenuRoleConnect record);

    List<MenuRoleConnect> selectByExample(MenuRoleConnectExample example);

    MenuRoleConnect selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MenuRoleConnect record, @Param("example") MenuRoleConnectExample example);

    int updateByExample(@Param("record") MenuRoleConnect record, @Param("example") MenuRoleConnectExample example);

    int updateByPrimaryKeySelective(MenuRoleConnect record);

    int updateByPrimaryKey(MenuRoleConnect record);
}