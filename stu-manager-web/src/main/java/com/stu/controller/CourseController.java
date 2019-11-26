package com.stu.controller;

import com.stu.pojo.StuCourse;
import com.stu.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    /**
     * 新增课程
     * @param course pojo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonResult courseAdd(@RequestBody StuCourse course){
        System.out.println(course);
        return JsonResult.ok();
    }
}
