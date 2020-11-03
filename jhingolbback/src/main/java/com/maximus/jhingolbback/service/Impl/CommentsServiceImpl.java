package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.CommentsMapper;
import com.maximus.jhingolbback.model.Comments;
import com.maximus.jhingolbback.model.CommentsExample;
import com.maximus.jhingolbback.service.CommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public int insert(Comments comments) {
        return commentsMapper.insert(comments);
    }

    @Override
    public List<Comments> getCommentList(String articleId) {
        CommentsExample example = new CommentsExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        List<Comments> result = commentsMapper.selectByExample(example);
        return result;
    }
}
