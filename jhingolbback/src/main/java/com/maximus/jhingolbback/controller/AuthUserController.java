package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.AuthUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("authUser")
public class AuthUserController {
    private static final Logger logger = LogManager.getLogger("AuthUserController");
    
    @Resource
    private AuthUserService authUserService;

    @PostMapping("getUser")
    @ResponseBody
    public Result<List<AuthUser>> getAuthUser(@RequestBody AuthUser user) {
        List<AuthUser> result = authUserService.getAuthUser(user);
        if(result.size() > 0) {
            return Result.success(result,"登录成功");
        }
        return Result.error("登录失败");
    }
}
