package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            PageInfo<Article> page = articleService.getFirstPageArticleList();
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
}
