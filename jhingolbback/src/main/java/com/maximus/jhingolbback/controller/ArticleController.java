package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.Enum.NumberEnum;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import com.maximus.jhingolbback.service.ArticleTagConnectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("article")
public class ArticleController {
    private static final Logger logger = LogManager.getLogger("ArticleController");
    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTagConnectService articleTagConnectService;

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
            //根据标签获取文章
            if(article.getTagId() != null && !"".equals(article.getTagId())) {
                List<String> ids = articleTagConnectService.getArticleIdByTagId(article.getTagId());
                article.setIds(ids);
            }
            //根据归档获取文章
            if(article.getModifiedTime() != null) {
                Date modifiedTime = article.getModifiedTime();
                Date firstDay = getFirstDayOfMonth(modifiedTime);
                Date lastDay = getLastDayOfMonth(modifiedTime);
                article.setFirstDay(firstDay);
                article.setLastDay(lastDay);
            }
            List<Article> list = articleService.getArticleList(article);
            PageInfo<Article> page = new PageInfo<>(list);
            page.setTotal(list.size());
            return Result.success(page,"获取文章列表");
        } catch (Exception e) {
            logger.error("can not find any information about",e);
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

    @GetMapping("getArticleDate")
    @ResponseBody
    public Result<List<Article>> getArticleDate() {
        try {
            Article article = new Article();
            List<Article> list = articleService.getArticleList(article);
            List<Article> result = new ArrayList<>();
            List<String> set = new ArrayList<>();

            for(Article article1 : list) {
                Date modifiedTime = article1.getModifiedTime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(modifiedTime);
                String dateString = getCurrentCNDate(cal);
                if(!set.contains(dateString)) {
                    set.add(dateString);
                    article1.setArchive(dateString);
                    result.add(article1);
                }
            }
            return Result.success(result,"成功");
        } catch (Exception e) {
            logger.error("失败" + e);
        }
        return Result.error("失败");
    }

    @PostMapping("addThumbsUp")
    @ResponseBody
    public Result<Integer> addThumbsUp(@RequestBody Article article) {
        article.setThumbsUp(1);
        try {
            int count = articleService.updateThumbsUp(article);
            if(count > 0) {
                Integer thumbsUp = article.getThumbsUp();
                return Result.success(thumbsUp,"成功");
            }
        } catch (Exception e) {
            logger.info("点赞失败",e);
        }
        return Result.error("失败");
    }

    @PostMapping("addOppose")
    @ResponseBody
    public Result<Integer> addOppose(@RequestBody Article article) {
        article.setOppose(1);
        try {
            int count = articleService.updateOppose(article);
            if(count > 0) {
                Integer oppose = article.getOppose();
                return Result.success(oppose,"成功");
            }
        } catch (Exception e) {
            logger.info("失败",e);
        }
        return Result.error("失败");
    }

    private String getCurrentCNDate(Calendar calendar) {
        StringBuilder CNDate = new StringBuilder();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        for (int i = 0; i < year.length(); i++) {
            CNDate.append(NumberEnum.getValueByKey(year.substring(i,i+1)));
        }
        CNDate.append("年");
        if(month.equals("10")) {
            CNDate.append("十");
        } else if(month.equals("11")) {
            CNDate.append("十一");
        } else if(month.equals("12")) {
            CNDate.append("十二");
        } else {
            for(int i = 0;i < month.length();i++) {
                if(month.charAt(i) == '0') {
                    continue;
                } else {
                    CNDate.append(NumberEnum.getValueByKey(month.substring(i, i + 1)));
                }
            }
        }
        CNDate.append("月");
        return CNDate.toString();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int first = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH,first);
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH,last);
        return cal.getTime();
    }
}
