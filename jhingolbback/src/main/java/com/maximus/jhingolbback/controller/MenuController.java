package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.Menu;
import com.maximus.jhingolbback.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("menu")
@Controller
public class MenuController {
    @Resource
    MenuService menuService;

    @GetMapping("getMenuList")
    @ResponseBody
    public List<Menu> getMenuList() {
        return menuService.getMenuByCurrentUser();
    }
}
