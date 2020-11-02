package com.maximus.jhingolbback.dao;

import com.maximus.jhingolbback.model.Comments;
import com.maximus.jhingolbback.model.CommentsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentsMapper {
    long countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExampleWithBLOBs(CommentsExample example);

    List<Comments> selectByExample(CommentsExample example);

    Comments selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);
}