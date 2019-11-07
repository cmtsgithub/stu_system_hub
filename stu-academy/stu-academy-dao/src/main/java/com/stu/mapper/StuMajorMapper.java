package com.stu.mapper;

import com.stu.pojo.StuMajor;

import java.util.List;

public interface StuMajorMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuMajor record);

    int insertSelective(StuMajor record);

    StuMajor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuMajor record);

    int updateByPrimaryKey(StuMajor record);

    List<StuMajor> selectByAcademyId(Integer id);

}