package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;

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
}
