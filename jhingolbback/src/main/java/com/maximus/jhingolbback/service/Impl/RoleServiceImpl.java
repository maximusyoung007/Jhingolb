package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.RoleMapper;
import com.maximus.jhingolbback.model.Role;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LogManager.getLogger(RoleServiceImpl.class);
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result<List<Role>> getRolesByUserName(String username) {
        try {
            List<Role> list = roleMapper.getRolesByUserName(username);
            if (list.size() > 0) {
                return Result.success(list,"成功");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return Result.error("失败");
    }
}
