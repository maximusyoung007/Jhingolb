package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.AuthUserExample;
import com.maximus.jhingolbback.service.Impl.AuthUserServiceImpl;

import javax.annotation.Resource;

public interface LoginService {
    /**
     * 用户登录
     */
    String login(String username,String password);
}
