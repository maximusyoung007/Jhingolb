package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.ArticleTagConnect;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import com.maximus.jhingolbback.service.ArticleTagConnectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            int currentPage = 1,pageSize = 5;
            PageHelper.startPage(1,5);
            List<Article> list = articleService.getArticleList(article);
            PageInfo<Article> page = new PageInfo<>(list);
            return Result.success(page,"获取列表成功");
        } catch(Exception e) {
            logger.error("can not find any information about ");
        }
        return Result.error("没有获取到数据");
    }

    @PostMapping("getArticleList")
    @ResponseBody
    public Result<PageInfo> getArticleList(@RequestBody Article article) {
        try {
            int currentPage = article.getCurrentPage(),pageSize = article.getPageSize();
            PageHelper.startPage(currentPage,pageSize);
            List<Article> list = articleService.getArticleList(article);
            PageInfo<Article> page = new PageInfo<>(list);
            return Result.success(page,"获取文章列表");
        } catch (Exception e) {
            logger.error("can not find any information about ");
        }
        return Result.error("没有回去到数据");
    }

    @PostMapping("addArticle")
    @ResponseBody
    public Result<String> addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PostMapping("getArticleById")
    @ResponseBody
    public Result<Article> getArticleById(@RequestBody Article article) {
        List<Article> list = articleService.getArticleList(article);
        if(list.size() > 0) {
            article = list.get(0);
            return Result.success(article,"成功");
        }
        return Result.error("失败");
    }
}
