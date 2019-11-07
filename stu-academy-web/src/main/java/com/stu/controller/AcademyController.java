package com.stu.controller;

import com.stu.pojo.StuAcademy;
import com.stu.service.StuAcademyService;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AcademyController {

    @Autowired
    private StuAcademyService stuAcademyService;

    /**
     * 返回一个List<StuAcademy> stuAcademies转换成的json字符串
     * @param callback 回调函数
     * @return 返回一个List<StuAcademy> stuAcademies转换成的json字符串
     */
    @RequestMapping(value = "/getStuAcademies", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getStuAcademies(@RequestParam("callback") String callback){
        List<StuAcademy> stuAcademies = stuAcademyService.selectAll();
        String json = JsonUtils.objectToJson(stuAcademies);
        if(callback != null){
            return callback+"("+ json +")";
        }
        return json;
    }
}
