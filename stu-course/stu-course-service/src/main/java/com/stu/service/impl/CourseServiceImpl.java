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
        List<StuCourse> stuCourseList = stuCourseMapper.selectAll(status);
        //上课时间格式转换
        for (StuCourse stuCourse : stuCourseList) {
            courseTimeConvert(stuCourse);
        }
        PageInfo<StuCourse> pageInfo = new PageInfo<>(stuCourseList);
        return pageInfo;
    }

    /**
     * 新增课程
     * @param record 课程pojo对象
     * @return 唯一主键  如果数据库语句报错，则返回0
     */
    @Override
    public int insertSelective(StuCourse record) {
        //完善stuCourse对象属性
        record.setStatus(4);// 1为可选 2为满人 3为已过期课程 4课程准备抢课
        record.setCreated(DateUtils.getNow());
        record.setUpdated(DateUtils.getNow());
        //调用服务层方法新增课程
        int result = stuCourseMapper.insertSelective(record);
        if(result == 0)
            return 0;
        Integer id = record.getId();
        return id;
    }

    /**
     * 课程日期转换
     * @param stuCourse pojo
     */
    private void courseTimeConvert(StuCourse stuCourse){
        //原本的时间
        String time = stuCourse.getTime();
        //转换的时间
        String convertTime = "";
        //字符串截取
        String substring = time.substring(0, 1);
        //判别单双周
        if(substring.equals("1")){
            convertTime = convertTime + "单周";
        } else if(substring.equals("2")){
            convertTime = convertTime + "双周";
        } else if(substring.equals("3")){
            convertTime = convertTime + "单双周";
        }
        //判断星期
        substring = time.substring(1, 2);
        convertTime = convertTime + "星期" + substring;
        //判别节数
        substring = time.substring(2);
        //切割字符串
        String[] split = substring.split("-");
        for (String s : split) {
            if(s.equals("1")){
                convertTime = convertTime + "1-2节,";
            } else if(s.equals("2")){
                convertTime = convertTime + "3-4节,";
            } else if(s.equals("3")){
                convertTime = convertTime + "5-6节,";
            } else if(s.equals("4")){
                convertTime = convertTime + "7-8节,";
            } else if(s.equals("5")){
                convertTime = convertTime + "9-10节,";
            } else if(s.equals("6")){
                convertTime = convertTime + "11-12节,";
            }
        }
        convertTime.substring(0, convertTime.length() - 1);
        //设置时间
        stuCourse.setTime(convertTime);
    }

    /**
     * 查询
     * @param id 主键
     * @return pojo
     */
    @Override
    public StuCourse selectByPrimaryKey(Integer id) {
        return stuCourseMapper.selectByPrimaryKey(id);
    }
}
