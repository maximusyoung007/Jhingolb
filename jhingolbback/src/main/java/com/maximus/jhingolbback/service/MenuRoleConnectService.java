package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Menu;
import com.maximus.jhingolbback.model.Role;

import java.util.List;

public interface MenuRoleConnectService {
    List<String> getMenuIdByRoleId(List<String> roleIds);
}
