package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategory();

    int addCategory(Category category);

    int deleteCategory(Category category);

    int updateCategory(Category category);
}
