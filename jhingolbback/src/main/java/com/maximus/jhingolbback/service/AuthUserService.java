package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.AuthUser;

import java.util.List;


public interface AuthUserService {
    List<AuthUser> getAuthUser(AuthUser user);
}
