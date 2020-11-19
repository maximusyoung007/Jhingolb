package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Role;
import com.maximus.jhingolbback.model.RoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    List<Role> getRolesByUserName(@Param("username") String username);
}