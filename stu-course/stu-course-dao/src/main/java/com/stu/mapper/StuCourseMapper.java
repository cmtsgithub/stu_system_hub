package com.stu.mapper;

import com.stu.pojo.StuCourse;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuCourse record);

    int insertSelective(StuCourse record);

    StuCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuCourse record);

    int updateByPrimaryKey(StuCourse record);

    List<StuCourse> selectAll(int status);
}