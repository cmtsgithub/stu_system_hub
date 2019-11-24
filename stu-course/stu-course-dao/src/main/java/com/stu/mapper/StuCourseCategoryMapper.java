package com.stu.mapper;

import com.stu.pojo.StuCourseCategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCourseCategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuCourseCategory record);

    int insertSelective(StuCourseCategory record);

    StuCourseCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuCourseCategory record);

    int updateByPrimaryKey(StuCourseCategory record);
}