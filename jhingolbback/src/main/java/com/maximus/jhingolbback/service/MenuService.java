package com.maximus.jhingolbback.service;

import com.maximus.jhingolbback.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuByCurrentUser();
}
