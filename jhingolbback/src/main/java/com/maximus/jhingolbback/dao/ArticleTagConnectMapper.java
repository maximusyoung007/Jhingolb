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

    int addArticleTagConnect(List<ArticleTagConnect> list);

    List<String> getArticleIdByTagId(@Param("tagId")String tagId);

    int deleteByArticleId(@Param("articleId")String articleId);
}