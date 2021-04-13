package com.maximus.jhingolbback.service.Impl;

import com.github.pagehelper.PageHelper;
import com.maximus.jhingolbback.dao.TagsMapper;
import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.model.TagsExample;
import com.maximus.jhingolbback.service.TagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TagsServiceImpl implements TagsService {
    @Resource
    private TagsMapper tagsMapper;

    @Override
    public List<Tags> getTagList() {
        TagsExample example = new TagsExample();
        List<Tags> list = tagsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Tags> getTagsListInUse() {
        return tagsMapper.getTagsListInUse();
    }

    @Override
    public int updateTagsById(Tags tags) {
        return tagsMapper.updateTagsById(tags);
    }

    @Override
    public int deleteTagsById(Tags tags) {
        return tagsMapper.deleteTagsById(tags);
    }

    @Override
    public int addTags(Tags tags) {
        return tagsMapper.addTags(tags);
    }

    @Override
    public int updateTags(List<Tags> tags) {
        return tagsMapper.updateTags(tags);
    }
}
