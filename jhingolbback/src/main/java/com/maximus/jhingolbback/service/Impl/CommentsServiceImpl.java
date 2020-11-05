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
    public List<Comments> getCommentList(Comments comments) {
        CommentsExample example = new CommentsExample();
        CommentsExample.Criteria criteria = example.createCriteria();
        if(comments.getArticleId() != null) {
            criteria.andArticleIdEqualTo(comments.getArticleId());
        }
        if(comments.getId() != null) {
            criteria.andIdEqualTo(comments.getId());
        }
        example.setOrderByClause("update_time");
        //查询所有的父评论
        criteria.andFatherIdEqualTo("0");
        List<Comments> result = commentsMapper.selectByExample(example);
        return result;
    }

    /**
     * 查询所有的子评论
     * @param comments
     * @return
     */
    @Override
    public List<Comments> getChildrenCommentList(Comments comments) {
        CommentsExample example = new CommentsExample();
        example.createCriteria().andFatherIdEqualTo(comments.getFatherId());
        example.setOrderByClause("update_time");
        List<Comments> result = commentsMapper.selectByExample(example);
        return result;
    }

    @Override
    public int updateComments(Comments comments) {
        return commentsMapper.updateByPrimaryKey(comments);
    }
}
