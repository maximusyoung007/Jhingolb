package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Comments;

import java.util.List;

public interface CommentsService {
    int insert(Comments comments);

    List<Comments> getCommentList(String articleId);
}
