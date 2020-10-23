package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.ArticleTagConnectMapper;
import com.maximus.jhingolbback.model.ArticleTagConnect;
import com.maximus.jhingolbback.service.ArticleTagConnectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTagConnectServiceImpl implements ArticleTagConnectService {
    @Resource
    ArticleTagConnectMapper tagConnectMapper;

    @Override
    public int addArticleTagConnect(List<ArticleTagConnect> list) {
        return tagConnectMapper.addArticleTagConnect(list);
    }
}
