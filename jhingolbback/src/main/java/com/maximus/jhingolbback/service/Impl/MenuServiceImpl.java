package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.MenuMapper;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.Menu;
import com.maximus.jhingolbback.model.MenuExample;
import com.maximus.jhingolbback.service.AuthUserService;
import com.maximus.jhingolbback.service.MenuRoleConnectService;
import com.maximus.jhingolbback.service.MenuService;
import com.maximus.jhingolbback.service.UserRoleConnectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    AuthUserService userService;

    @Resource
    UserRoleConnectService userRoleConnectService;

    @Resource
    MenuRoleConnectService menuRoleConnectService;

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByCurrentUser() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        AuthUser user = new AuthUser();
        user.setUsername(username);
        List<AuthUser> userList = userService.getAuthUser(user);
        if(userList.size() == 1) {
            user = userList.get(0);
        }
        List<String> roleIds = userRoleConnectService.getAllRoleIdByUserInfo(user);
        List<String> menuIds = menuRoleConnectService.getMenuIdByRoleId(roleIds);
        List<Menu> menus = new ArrayList<>();
        if(menuIds != null && menuIds.size() > 0) {
            menus = menuMapper.selectByIds(menuIds);
        }
        handleTree(menus);
        return menus;
    }

    public void handleTree(List<Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = menuMapper.getMenusByParentsId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> !"0".equals(m.getParentId()));

    }
}
