package com.stu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.mapper.StuCourseMapper;
import com.stu.pojo.StuCourse;
import com.stu.service.CourseService;
import com.stu.utils.DateUtils;
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

    /**
     * 新增课程
     * @param record 课程pojo对象
     * @return 唯一主键
     */
    @Override
    public int insertSelective(StuCourse record) {
        //完善stuCourse对象属性
        record.setStatus(1);// 1为可选 2为满人 3为已过期课程
        record.setCreated(DateUtils.getNow());
        record.setUpdated(DateUtils.getNow());
        //调用服务层方法新增课程
        return stuCourseMapper.insertSelective(record);
    }

}
