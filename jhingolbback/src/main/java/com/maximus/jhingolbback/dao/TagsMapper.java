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

    int updateTagsById(Tags tags);

    int deleteTagsById(Tags tags);

    int addTags(Tags tags);

    int updateTags(List<Tags> tags);

    int updateTagsUseCount(List<Tags> tags);
}