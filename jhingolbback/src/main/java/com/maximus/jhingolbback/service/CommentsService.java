package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Comments;

import java.util.List;

public interface CommentsService {
    int insert(Comments comments);

    List<Comments> getCommentList(Comments comments);

    List<Comments> getChildrenCommentList(Comments comments);

    int updateComments(Comments comments);
}
