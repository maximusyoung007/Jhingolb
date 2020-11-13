package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.Role;

import java.util.List;

public interface UserRoleConnectService {
    List<String> getAllRoleIdByUserInfo(AuthUser user);
}
