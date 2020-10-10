package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.dao.TestDao;
import com.maximus.jhingolbback.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public List<String> getTestName() {
        return testDao.getTestName();
    }
}
