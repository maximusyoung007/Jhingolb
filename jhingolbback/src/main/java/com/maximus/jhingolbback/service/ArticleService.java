package com.maximus.jhingolbback.service;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;

public interface ArticleService {
    /**
     * @author maximus
     * @return
     */
    PageInfo<Article> getArticleList(Article article);
}
