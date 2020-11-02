package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.TagsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("tags")
public class TagsController {
    @Resource
    private TagsService tagsService;

    @RequestMapping("getTagsList")
    @ResponseBody
    public Result<List<Tags>> getTagsList() {
        List<Tags> result = tagsService.getTagList();
        if(result.size() > 0) {
            return Result.success(result,"成功");
        }
        return Result.error("失败");
    }

    @RequestMapping("getTagsListInUse")
    @ResponseBody
    public Result<List<Tags>> getTagsListInUse() {
        List<Tags> result = tagsService.getTagsListInUse();
        if(result.size() > 0) {
            return Result.success(result,"成功");
        }
        return Result.error("失败");
    }
}
