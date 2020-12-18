package com.maximus.jhingolbback.job;

import com.maximus.jhingolbback.dao.ArticleMapper;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.util.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisJob extends QuartzJobBean {
    private static final Logger log = LogManager.getLogger(RedisJob.class);

    @Resource
    RedisUtil redisUtil;

    @Resource
    ArticleMapper articleMapper;

    //定时任务，每天凌晨3点同步阅读量到数据库
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Article article = new Article();
        List<Article> articleList = articleMapper.getArticleList(article);
        List<Map<String,String>> list = new ArrayList<>();
        for(int i = 0;i < articleList.size();i++) {
            String id = articleList.get(i).getId();
            if(redisUtil.hasKey(id)) {
                Map<String,String> viewsMap = new HashMap<>();
                String views = redisUtil.get(id).toString();
                viewsMap.put("id",id);
                viewsMap.put("views",views);
                list.add(viewsMap);
                redisUtil.del(id);
            }
        }
        int count = articleMapper.updateViews(list);
        log.info("定时任务执行了");
    }
}
