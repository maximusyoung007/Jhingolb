package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.ArticleMapper;
import com.maximus.jhingolbback.dao.ArticleTagConnectMapper;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.ArticleTagConnect;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger log = LogManager.getLogger(ArticleServiceImpl.class);
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTagConnectMapper articleTagConnectMapper;

    /**
     * @author maximus
     * @return
     */
    @Override
    public List<Article> getArticleList(Article article) {
        List<Article> list = articleMapper.getArticleList(article);
        return list;
    }

    /**
     * @author maximus
     * 添加文章
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public Result<String> addArticle(Article article) {
        Integer releaseState = article.getReleaseState();
        String uuid = UUID.randomUUID().toString();
        article.setId(uuid);

        int count = articleMapper.insertSelective(article);
        List<String> tagsNames = article.getAllTags();
        List<ArticleTagConnect> atcList = new ArrayList<>();

        for(int i = 0;i < tagsNames.size();i++) {
            ArticleTagConnect articleTagConnect = new ArticleTagConnect();
            articleTagConnect.setTagName(tagsNames.get(i));
            articleTagConnect.setArticleId(uuid);
            atcList.add(articleTagConnect);
        }
        int count1 = articleTagConnectMapper.addArticleTagConnect(atcList);
        if(count > 0 && count1 > 0) {
            if(releaseState == 0) {
                return Result.success("保存文章成功");
            } else if(releaseState == 1) {
                return Result.success("发布文章成功");
            } else {
                return Result.error("请保存或发布文章");
            }
        }

        return Result.error("保存或发布文章失败");
    }

    @Override
    public int updateThumbsUp(Article record) {
        return articleMapper.updateThumbsUp(record);
    }

    @Override
    public int updateOppose(Article article) {
        return articleMapper.updateOppose(article);
    }
}
