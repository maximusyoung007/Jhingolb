package com.maximus.jhingolbback.service;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;

public interface ArticleService {
    PageInfo<Article> getArticleList();
}
