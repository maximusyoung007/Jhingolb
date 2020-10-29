package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.Category;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("getCategoryList")
    @ResponseBody
    public Result<List<Category>> getCategoryList() {
        List<Category> list = categoryService.getCategory();
        if(list.size() > 0) {
            return Result.success(list,"成功");
        }
        return Result.error("失败");
    }
}
