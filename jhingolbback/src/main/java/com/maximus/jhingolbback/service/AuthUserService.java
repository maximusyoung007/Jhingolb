package com.maximus.jhingolbback.service;

import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.model.AuthUser;


public interface AuthUserService {
    PageInfo<AuthUser> getAuthUser();
}
