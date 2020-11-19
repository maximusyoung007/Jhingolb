package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Menu;
import com.maximus.jhingolbback.model.MenuExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuMapper {
    List<Menu> selectByExample(MenuExample example);

}