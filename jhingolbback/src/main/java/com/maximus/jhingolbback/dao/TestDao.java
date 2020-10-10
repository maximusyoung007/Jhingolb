package com.maximus.jhingolbback.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {
    List<String> getTestName();
}
