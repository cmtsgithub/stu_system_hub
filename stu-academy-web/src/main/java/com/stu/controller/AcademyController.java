package com.stu.controller;

import com.stu.pojo.StuAcademy;
import com.stu.service.StuAcademyService;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AcademyController {

    @Autowired
    private StuAcademyService stuAcademyService;

    /**
     *
     * @return 返回一个json数据，json数据包含一个data，data = List<StuAcademy> stuAcademies
     */
    @RequestMapping(value = "/getStuAcademies", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getStuAcademies(){
        List<StuAcademy> stuAcademies = stuAcademyService.selectAll();
        return JsonResult.build(200, "请求成功!", stuAcademies);
    }
}
