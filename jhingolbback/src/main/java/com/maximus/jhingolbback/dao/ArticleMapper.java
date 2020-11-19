package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.ArticleExample;
import com.maximus.jhingolbback.model.ArticleWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    int updateArticle(Article article);

    int updateThumbsUp(Article record);

    int updateOppose(Article article);

    List<Article> getArticleList(Article article);

}