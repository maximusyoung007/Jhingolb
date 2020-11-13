package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.MenuRoleConnectMapper;
import com.maximus.jhingolbback.model.MenuRoleConnect;
import com.maximus.jhingolbback.model.MenuRoleConnectExample;
import com.maximus.jhingolbback.service.MenuRoleConnectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuRoleConnectServiceImpl implements MenuRoleConnectService {
    @Resource
    private MenuRoleConnectMapper menuRoleConnectMapper;

    @Override
    public List<String> getMenuIdByRoleId(List<String> roleIds) {
        List<String> result = new ArrayList<>();
        for(String roleId : roleIds) {
            MenuRoleConnectExample menuRoleConnectExample = new MenuRoleConnectExample();
            menuRoleConnectExample.createCriteria().andRidEqualTo(roleId);
            List<MenuRoleConnect> menuRoleConnectList = menuRoleConnectMapper.selectByExample(menuRoleConnectExample);
            List<String> menuId = new ArrayList<>();
            for(MenuRoleConnect connect : menuRoleConnectList) {
                menuId.add(connect.getMid());
            }
            result.addAll(menuId);
        }
        return result;
    }
}
