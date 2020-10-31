package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.ArticleTagConnect;
import com.maximus.jhingolbback.model.ArticleTagConnectExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleTagConnectMapper {
    long countByExample(ArticleTagConnectExample example);

    int deleteByExample(ArticleTagConnectExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArticleTagConnect record);

    int insertSelective(ArticleTagConnect record);

    List<ArticleTagConnect> selectByExample(ArticleTagConnectExample example);

    ArticleTagConnect selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ArticleTagConnect record, @Param("example") ArticleTagConnectExample example);

    int updateByExample(@Param("record") ArticleTagConnect record, @Param("example") ArticleTagConnectExample example);

    int updateByPrimaryKeySelective(ArticleTagConnect record);

    int updateByPrimaryKey(ArticleTagConnect record);

    int addArticleTagConnect(List<ArticleTagConnect> list);

    List<String> getArticleIdByTagId(@Param("tagId")String tagId);
}