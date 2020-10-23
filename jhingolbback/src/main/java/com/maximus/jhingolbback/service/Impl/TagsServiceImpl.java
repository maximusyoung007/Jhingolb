package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.TagsMapper;
import com.maximus.jhingolbback.model.Tags;
import com.maximus.jhingolbback.model.TagsExample;
import com.maximus.jhingolbback.service.TagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}