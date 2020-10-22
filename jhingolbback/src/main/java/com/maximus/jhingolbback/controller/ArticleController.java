package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("article")
public class ArticleController {
    private static final Logger logger = LogManager.getLogger("ArticleController");
    @Resource
    private ArticleService articleService;

    @GetMapping("getFirstPageArticleList")
    @ResponseBody
    public Result<PageInfo> getFirstPageArticleList() {
        try {
            Article article = new Article();
            article.setCurrentPage(1);
            article.setPageSize(5);
            PageInfo<Article> page = articleService.getArticleList(article);
            return Result.success(page,"获取列表成功");
        } catch(Exception e) {
            logger.error("can not find any information about ");
        }
        return Result.error("没有获取到数据");
    }

    @PostMapping("addArticle")
    @ResponseBody
    public Result<String> addArticle() {
        return Result.error("");
    }

    @PostMapping("getArticleList")
    @ResponseBody
    public Result<PageInfo> getArticleList(@RequestBody Article article) {
        try {
            PageInfo<Article> page = articleService.getArticleList(article);
            return Result.success(page,"获取文章列表");
        } catch (Exception e) {
            logger.error("can not find any information about ");
        }
        return Result.error("没有回去到数据");
    }
}
