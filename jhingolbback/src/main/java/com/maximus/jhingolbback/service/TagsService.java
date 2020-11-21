package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Tags;

import java.util.List;

public interface TagsService {
    public List<Tags> getTagList();

    List<Tags> getTagsListInUse();

    int updateTagsById(Tags tags);

    int deleteTagsById(Tags tags);

    int addTags(Tags tags);
}
