package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.ArticleMapper;
import com.maximus.jhingolbback.dao.ArticleTagConnectMapper;
import com.maximus.jhingolbback.dao.TagsMapper;
import com.maximus.jhingolbback.model.Article;
import com.maximus.jhingolbback.model.ArticleTagConnect;
import com.maximus.jhingolbback.model.Category;
import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.ArticleService;
import com.maximus.jhingolbback.service.CategoryService;
import com.maximus.jhingolbback.service.TagsService;
import com.maximus.jhingolbback.util.IpUtil;
import com.maximus.jhingolbback.util.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private TagsService tagsService;

    @Resource
    private CategoryService categoryService;

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
        //更新分类使用次数
        String categoryId = article.getCategoryId();
        Category category = new Category();
        category.setId(categoryId);
        category.setUseCounts(1);
        categoryService.updateCategory(category);
        List<Tags> tagsList = article.getTags();
        List<ArticleTagConnect> atcList = new ArrayList<>();

        for(Tags tags : tagsList) {
            ArticleTagConnect articleTagConnect = new ArticleTagConnect();
            articleTagConnect.setTagName(tags.getName());
            articleTagConnect.setTagId(tags.getId());
            articleTagConnect.setArticleId(uuid);
            atcList.add(articleTagConnect);
        }
        //添加标签和文章关系
        int count1 = articleTagConnectMapper.addArticleTagConnect(atcList);
        if(count > 0 && count1 > 0) {
            //更新标签使用次数
            int count2 = tagsService.updateTags(tagsList);
            if (count2 > 0) {
                if (releaseState == 0) {
                    return Result.success(uuid, "保存文章成功");
                } else if (releaseState == 1) {
                    return Result.success(uuid, "发布文章成功");
                } else {
                    return Result.error("请保存或发布文章");
                }
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

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public Result<String> updateArticleInfo(Article article) {
        int count = articleMapper.updateArticle(article);
        List<Tags> tagsList = article.getTags();
        //删除所有的该文章ID对应的标签
        int count1 = articleTagConnectMapper.deleteByArticleId(article.getId());
        //将新增的文章标签存到表中
        List<ArticleTagConnect> list = new ArrayList<>();
        for(Tags tags : tagsList) {
            ArticleTagConnect articleTagConnect = new ArticleTagConnect();
            articleTagConnect.setTagId(tags.getId());
            articleTagConnect.setTagName(tags.getName());
            articleTagConnect.setArticleId(article.getId());
            list.add(articleTagConnect);
        }
        int count2 = 0;
        if(list.size() > 0) {
            count2 = articleTagConnectMapper.addArticleTagConnect(list);
        }
        //更新标签使用次数
        int count3 = tagsService.updateTagsUseCount(tagsList);

        if(count > 0 && count1 > 0 && count2 > 0 && count3 > 0) {
            return Result.success("成功","成功");
        } else {
            return Result.error("失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public Result<String> deleteArticle(Article article) {
        int count1 = articleMapper.deleteByPrimaryKey(article.getId());
        int count2 = articleTagConnectMapper.deleteByArticleId(article.getId());
        if(count1 > 0 && count2 > 0) {
            return Result.success("成功");
        }
        return Result.error("失败");
    }

    @Override
    public Result<String> getViews(Article article) {
        Integer views = 0;
        List<Article> articleList = articleMapper.getArticleList(article);
        Article article1 = articleList.get(0);
        views = article1.getViews();
        return Result.success(views.toString(),"成功");
    }

}
