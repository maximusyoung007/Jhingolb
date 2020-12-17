package com.maximus.jhingolbback.job;

import com.maximus.jhingolbback.dao.ArticleMapper;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.util.RedisUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

public class RedisJob extends QuartzJobBean {
    @Resource
    RedisUtil redisUtil;

    @Resource
    ArticleMapper articleMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        String id = "123456";
//        Integer views = Integer.valueOf(redisUtil.get(id).toString());
//        Article article = new Article();
//        article.setId(id);
//        article.setViews(views);
//        articleMapper.updateArticle(article);
        System.out.println("这是一个定时任务" + jobExecutionContext.getJobDetail().getJobDataMap().get("msg"));
    }
}
