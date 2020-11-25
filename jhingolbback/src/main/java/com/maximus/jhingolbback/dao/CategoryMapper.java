package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Category;
import com.maximus.jhingolbback.model.CategoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {
    List<Category> selectByExample(CategoryExample example);

    int addCategory(Category category);

    int deleteCategory(Category category);

    int updateCategory(Category category);
}