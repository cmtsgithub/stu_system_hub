package com.stu.controller;

import com.stu.pojo.StuAcademy;
import com.stu.service.StuAcademyService;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据院系编号，返回一个院系对象
     * @param callback 回调函数
     * @param academyId 院系编号
     * @return String 回调函数 + 信息
     */
    @ResponseBody
    @RequestMapping(value = "/getStuAcademy", method = RequestMethod.GET,  produces= "text/plain;charset=UTF-8")
    public String getStuAcademy(@RequestParam Integer academyId, @RequestParam("callback") String callback){
        StuAcademy stuAcademy = stuAcademyService.selectByPrimaryKey(academyId);
        if(stuAcademy != null){
            String json = JsonUtils.objectToJson(stuAcademy);
            if(callback != null){
                return callback+"("+ json +")";
            }
            return json;
        }
        return null;
    }
}
