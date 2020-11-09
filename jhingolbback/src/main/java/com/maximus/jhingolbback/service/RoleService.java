package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Role;
import com.maximus.jhingolbback.result.Result;

import java.util.List;

public interface RoleService {
    Result<List<Role>> getRolesByUserName(String username);
}
