package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ArticleService {
    /**
     * @author maximus
     * @return
     */
    List<Article> getArticleList(Article article);

    /**
     * @author maximus
     * 添加文章
     */
    Result<String> addArticle(Article record);

    int updateThumbsUp(Article record);

    int updateOppose(Article article);

    Result<String> updateArticleInfo(Article article);

    Result<String> deleteArticle(Article article);

    Result<String> getViews(Article article);

    void addViews(Article article,HttpServletRequest request);
}
