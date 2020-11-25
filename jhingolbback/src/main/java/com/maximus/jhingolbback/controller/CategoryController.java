package com.maximus.jhingolbback.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.Category;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("getCategoryList")
    @ResponseBody
    public Result<PageInfo<Category>> getCategoryList(@RequestBody Map<String,Object> map) {
        int currentPage = 0,pageSize = 0;
        if(map.get("currentPage") != null) {
            currentPage = (Integer) map.get("currentPage");
        }
        if(map.get("pageSize") != null) {
            pageSize = (Integer) map.get("pageSize");
        }
        PageHelper.startPage(currentPage,pageSize);
        List<Category> list = categoryService.getCategory();
        list.stream().forEach(item->{
            item.setEdit(0);
            item.setInputName("");
        });
        PageInfo<Category> result = new PageInfo<>(list);
        if(result.getList().size() > 0) {
            return Result.success(result,"成功");
        }
        return Result.error("失败");
    }

    @GetMapping("getCategoryShow")
    @ResponseBody
    public Result<List<Category>> getCategoryShow() {
        List<Category> result = categoryService.getCategory();
        if(result.size() > 0) {
            return Result.success(result,"成功");
        }
        return Result.error("失败");
    }

    @PostMapping("addCategory")
    @ResponseBody
    public Result<String> addCategory(@RequestBody Category category) {
        int count = categoryService.addCategory(category);
        if(count > 0) {
            return Result.success("成功");
        }
        return Result.error("失败");
    }

    @PostMapping("deleteCategory")
    @ResponseBody
    public Result<String> deleteCategory(@RequestBody Category category) {
        int count = categoryService.deleteCategory(category);
        if(count > 0) {
            return Result.success("成功");
        }
        return Result.error("失败");
    }

    @PostMapping("updateCategory")
    @ResponseBody
    public Result<String> updateCategory(@RequestBody Category category) {
        int count = categoryService.updateCategory(category);
        if(count > 0) {
            return Result.success("成功");
        }
        return Result.error("失败");
    }
}
