package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.model.TagsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagsMapper {
    List<Tags> selectByExample(TagsExample example);

    List<Tags> getTagsListInUse();
}