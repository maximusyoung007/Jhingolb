package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.UserRoleConnectMapper;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.Menu;
import com.maximus.jhingolbback.model.UserRoleConnect;
import com.maximus.jhingolbback.model.UserRoleConnectExample;
import com.maximus.jhingolbback.service.UserRoleConnectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleConnectServiceImpl implements UserRoleConnectService {
    @Resource
    UserRoleConnectMapper userRoleConnectMapper;

    /**
     * 根据用户ID或用户名查询该用户对应的所有角色
     * @param user
     * @return
     */
    @Override
    public List<String> getAllRoleIdByUserInfo(AuthUser user) {
        UserRoleConnectExample userRoleConnectExample = new UserRoleConnectExample();
        UserRoleConnectExample.Criteria criteria = userRoleConnectExample.createCriteria();
        if(user.getId() != null) {
            criteria.andUserIdEqualTo(user.getId().toString());
        }
        List<UserRoleConnect> list = userRoleConnectMapper.selectByExample(userRoleConnectExample);
        List<String> result = new ArrayList<>();
        for(UserRoleConnect connect : list) {
            result.add(connect.getRoleId());
        }
        return result;
    }

    public void handleMenus(List<Menu> menus) {

    }
}
