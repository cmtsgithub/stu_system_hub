package com.stu.service;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuCourse;

import java.util.List;

public interface CourseService {

    List<StuCourse> selectAll(int status);

    PageInfo<StuCourse> selectCourseByPageNum(int pageNum, int pageSize, int status);
}
