package com.maximus.jhingolbback.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.service.AuthUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("authUser")
public class AuthUserController {
    @Resource
    private AuthUserService authUserService;

    @RequestMapping("getUser")
    @ResponseBody
    public JSONObject getAuthUser() {
        JSONObject outJson = new JSONObject();
        PageInfo<AuthUser> result = authUserService.getAuthUser();
        outJson.put("users",result);
        return outJson;
    }
}
