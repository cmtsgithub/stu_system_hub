package com.stu.controller;

import com.stu.pojo.StuCourse;
import com.stu.service.CourseService;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 新增课程
     * @param course pojo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonResult courseAdd(@RequestBody StuCourse course){
        //调用服务层方法，该方法会返回课程主键id
        int result = courseService.insertSelective(course);
        //判断返回值
        if(result == 0){
            return JsonResult.build(400, "课程新建失败，请重试");
        }
        StuCourse stuCourse = courseService.selectByPrimaryKey(result);
        //把对象转换成json串返回
        return JsonResult.ok(JsonUtils.objectToJson(stuCourse));
    }

    /**
     * 改变课程状态
     * @param id 课程主键id
     * @param status 课程状态 1 可选 2 满人 3课程已删除 4 课程准备开始抢课阶段
     * @return json串
     */
    @ResponseBody
    @RequestMapping(value = "/courseStatus/{id}/{status}", method = RequestMethod.POST)
    public JsonResult updateCourseStatus(@PathVariable Integer id, @PathVariable Integer status){
        //根据id查询课程
        //判断返回的课程pojo是否为null
        //若不为空，进行下一步
        //改变课程的状态
        //判断返回值
        //返回Json数据
        return JsonResult.ok();
    }

}
