package com.maximus.jhingolbback.service;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;

public interface ArticleService {
    /**
     * @author maximus
     * @return
     */
    PageInfo<Article> getArticleList(Article article);

    /**
     * @author maximus
     * 添加文章
     */
    Result<String> addArticle(Article record);
}
