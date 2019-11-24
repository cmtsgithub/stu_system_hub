package com.stu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.mapper.StuCourseMapper;
import com.stu.pojo.StuCourse;
import com.stu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private StuCourseMapper stuCourseMapper;

    /**
     * 查询全部课程
     * @param status 课程状态 1可选 2满人 3已被删除
     * @return List<StuCourse>
     */
    @Override
    public List<StuCourse> selectAll(int status) {
        return stuCourseMapper.selectAll(status);
    }

    /**
     * 分页查询课程
     * @param pageNum 页码
     * @param pageSize 显示条数
     * @param status 课程状态 1可选 2满人 3已被删除
     * @return PageInfo<StuCourse>
     */
    @Override
    public PageInfo<StuCourse> selectCourseByPageNum(int pageNum, int pageSize, int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<StuCourse> stuCourseList = stuCourseMapper.selectAll(1);
        PageInfo<StuCourse> pageInfo = new PageInfo<>(stuCourseList);
        return pageInfo;
    }
}
