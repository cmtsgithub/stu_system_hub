package com.stu.mapper;

import com.stu.pojo.StuSc;

public interface StuScMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuSc record);

    int insertSelective(StuSc record);

    StuSc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuSc record);

    int updateByPrimaryKey(StuSc record);
}