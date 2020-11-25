package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.TagsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tags")
public class TagsController {
    @Resource
    private TagsService tagsService;

    @PostMapping("getTagsList")
    @ResponseBody
    public Result<PageInfo<Tags>> getTagsList(@RequestBody Map<String,Object> map) {
        int currentPage = 0,pageSize = 0;
        if(map.get("currentPage") != null) {
            currentPage = (Integer) map.get("currentPage");
        }
        if(map.get("pageSize") != null) {
            pageSize = (Integer) map.get("pageSize");
        }
        //PageHelper.startPage(currentPage,pageSize);
        List<Tags> list = tagsService.getTagList(map);
        list.stream().forEach(item->{
            item.setEdit(0);
            item.setInputName("");
        });
        PageInfo<Tags> result = new PageInfo<>(list);
        if(result.getList().size() > 0) {
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

    @PostMapping("updateTags")
    @ResponseBody
    public Result<String> updateTagsById(@RequestBody Tags tags) {
        int count = tagsService.updateTagsById(tags);
        if(count > 0) {
            return Result.success("成功");
        }
        return Result.error("失败");
    }

    @PostMapping("deleteTags")
    @ResponseBody
    public Result<String> deleteTagsById(@RequestBody Tags tags) {
        int count = tagsService.deleteTagsById(tags);
        if(count > 0) {
            return Result.success("成功","成功");
        }
        return Result.error("失败");
    }

    @PostMapping("addTag")
    @ResponseBody
    public Result<String> addTag(@RequestBody Tags tags) {
        tags.setUseCounts(0);
        int count = tagsService.addTags(tags);
        if(count > 0) {
            return Result.success("成功","成功");
        }
        return Result.error("失败");
    }
}
