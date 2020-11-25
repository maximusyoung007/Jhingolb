package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.CategoryMapper;
import com.maximus.jhingolbback.model.Category;
import com.maximus.jhingolbback.model.CategoryExample;
import com.maximus.jhingolbback.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategory() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("update_time desc");
        List<Category> list = categoryMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addCategory(Category category) {
        int count = categoryMapper.addCategory(category);
        return count;
    }

    @Override
    public int deleteCategory(Category category) {
        return categoryMapper.deleteCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }
}
