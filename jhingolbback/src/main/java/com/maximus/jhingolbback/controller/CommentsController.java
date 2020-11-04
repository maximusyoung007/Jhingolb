package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.Comments;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.CommentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("comments")
public class CommentsController {
    private static final Logger logger = LogManager.getLogger("CommentsController");
    @Resource
    private CommentsService commentsService;

    @RequestMapping("addComments")
    @ResponseBody
    public Result<Integer> addComments(@RequestBody Comments comments) {
        if(comments.getFatherId() == null) {
            comments.setFatherId("0");
        }
        try {
            int count = commentsService.insert(comments);
            if(count > 0) {
                Integer commentCounts = comments.getCommentCounts();
                return Result.success(commentCounts,"评论成功");
            }
        } catch(Exception e) {
            logger.info("新增评论失败",e);
        }
        return Result.error("评论失败");
    }

    @RequestMapping("getCommentList")
    @ResponseBody
    public Result<List<Comments>> getCommentList(@RequestBody Comments comments) {
        List<Comments> result = new ArrayList<>();
        if(comments.getArticleId() != null || comments.getId() != null) {
            result = commentsService.getCommentList(comments);
        }
        if(result.size() > 0) {
            for(Comments comments1 : result) {
                if(comments1.getId() != null && !"0".equals(comments1.getId())) {
                    comments1.setFatherId(comments1.getId());
                    List<Comments> childrenList = commentsService.getChildrenCommentList(comments1);
                    comments1.setChildrenComments(childrenList);
                }
            }
            return Result.success(result,"成功");
        }
        return Result.error("失败");
    }

    @RequestMapping("updateComments")
    @ResponseBody
    public Result<String> updateCommentList(@RequestBody Comments comments) {
        try{
            int count = commentsService.updateComments(comments);
            return Result.success("修改成功","修改成功");
        } catch (Exception e) {
            logger.error("修改失败",e);
        }
        return Result.error("修改失败");
    }
}
