package com.maximus.jhingolbback.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maximus.jhingolbback.dao.AuthUserMapper;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.AuthUserExample;
import com.maximus.jhingolbback.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserMapper authUserMapper;

    @Override
    public PageInfo<AuthUser> getAuthUser() {
        int currentPage = 1,pageSize = 10;
        PageHelper.startPage(currentPage,pageSize);
        AuthUserExample userExample = new AuthUserExample();
        List<AuthUser> list = authUserMapper.selectByExample(userExample);
        PageInfo<AuthUser> page = new PageInfo<AuthUser>(list);
        return page;
    }

}
