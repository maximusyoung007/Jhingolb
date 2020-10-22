package com.maximus.jhingolbback.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.dao.ArticleMapper;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.ArticleExample;
import com.maximus.jhingolbback.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    /**
     * @author maximus
     * @return
     */
    @Override
    public PageInfo<Article> getArticleList(Article article) {
        int currentPage = article.getCurrentPage(),pageSize = article.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andReleaseStateEqualTo(1);
        List<Article> list = articleMapper.selectByExample(articleExample);
        PageInfo<Article> page = new PageInfo<Article>(list);
        return page;
    }
}
