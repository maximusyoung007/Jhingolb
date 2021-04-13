package com.maximus.jhingolbback.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.Enum.NumberEnum;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import com.maximus.jhingolbback.service.ArticleTagConnectService;
import com.maximus.jhingolbback.service.TagsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("article")
public class ArticleController {
    private static final Logger logger = LogManager.getLogger("ArticleController");
    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTagConnectService articleTagConnectService;

    @Resource
    private TagsService tagsService;

    @GetMapping("getFirstPageArticleList")
    @ResponseBody
    public Result<List<Article>> getFirstPageArticleList() {
        try {
            Article article = new Article();
            article.setReleaseState(1);
            List<Article> list = articleService.getArticleList(article);
            List<Article> limitList = list.stream().sorted(Comparator.comparing(Article::getModifiedTime)).limit(5).collect(Collectors.toList());
            return Result.success(limitList,"获取列表成功");
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
            if(article.getIsManage() != null) {
                if (article.getIsManage() == 1) {
                    article.setReleaseState(null);
                } else {
                    article.setReleaseState(1);
                }
            } else {
                article.setReleaseState(1);
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
    public Result<String> addArticle(@RequestBody Map<String,Object> map) {
        Article article = new Article();
        article.setTitle(map.get("title").toString());
        article.setArticleBody(map.get("articleBody").toString());
        article.setReleaseState((Integer)map.get("releaseState"));
        String summary = map.get("summary").toString();
        article.setSummary(map.get("summary").toString());
        List<Tags> tagsList = new ArrayList<>();
        List<LinkedHashMap> hashMapList = new ArrayList<>();
        Object obj = map.get("allTags");
        if(obj instanceof ArrayList<?>) {
            for(Object o : (List<?>) obj) {
                hashMapList.add(LinkedHashMap.class.cast(o));
            }
        }
        for(LinkedHashMap hashMap : hashMapList) {
            Tags tag = new Tags();
            tag.setId(hashMap.get("id").toString());
            tag.setName(hashMap.get("name").toString());
            tagsList.add(tag);
        }
        article.setTags(tagsList);
        article.setCategoryId(map.get("category").toString());
        article.setViews((Integer)map.get("views"));
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

    @PostMapping("updateArticle")
    @ResponseBody
    public Result<String> updateArticle(@RequestBody Map<String,Object> map) {
        Article article = new Article();
        article.setTitle(map.get("title").toString());
        article.setArticleBody(map.get("articleBody").toString());
        List<Tags> tags = new ArrayList<>();
        List<LinkedHashMap> tem = new ArrayList<>();
        Object obj = map.get("allTags");
        if(obj instanceof ArrayList<?>) {
            for(Object o : (List<?>) obj) {
                tem.add(LinkedHashMap.class.cast(o));
            }
        }
        for(LinkedHashMap hashMap : tem) {
            Tags tags1 = new Tags();
            tags1.setId(hashMap.get("id").toString());
            tags1.setName(hashMap.get("name").toString());
            tags.add(tags1);
        }
        article.setTags(tags);
        article.setCategory(map.get("category").toString());
        article.setReleaseState((Integer) map.get("releaseState"));
        article.setId(map.get("articleId").toString());
        article.setSummary(map.get("summary").toString());
        return articleService.updateArticleInfo(article);
    }

    @RequestMapping("deleteArticle")
    @ResponseBody
    public Result<String> deleteArticle(@RequestBody Article article) {
        return articleService.deleteArticle(article);
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

    @RequestMapping("getViewsById")
    @ResponseBody
    public Result<String> getViewsById(@RequestBody Article article) {
        Result<String> result = articleService.getViews(article);
        return result;
    }

//    @RequestMapping("addViews")
//    @ResponseBody
//    public Result addViews(@RequestBody Article article,HttpServletRequest request) {
//        articleService.addViews(article,request);
//        return Result.success("1");
//    }

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
