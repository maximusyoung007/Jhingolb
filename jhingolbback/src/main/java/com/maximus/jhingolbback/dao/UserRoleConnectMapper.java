package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.UserRoleConnect;
import com.maximus.jhingolbback.model.UserRoleConnectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleConnectMapper {
    long countByExample(UserRoleConnectExample example);

    int deleteByExample(UserRoleConnectExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRoleConnect record);

    int insertSelective(UserRoleConnect record);

    List<UserRoleConnect> selectByExample(UserRoleConnectExample example);

    UserRoleConnect selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRoleConnect record, @Param("example") UserRoleConnectExample example);

    int updateByExample(@Param("record") UserRoleConnect record, @Param("example") UserRoleConnectExample example);

    int updateByPrimaryKeySelective(UserRoleConnect record);

    int updateByPrimaryKey(UserRoleConnect record);
}