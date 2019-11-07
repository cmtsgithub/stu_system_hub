package com.stu.controller;

import com.stu.pojo.StuMajor;
import com.stu.service.StuMajorService;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MajorController {

    @Autowired
    private StuMajorService stuMajorService;

    /**
     * 通过院系主键来查询专业
     * @param callback 回调函数名
     * @param academyId 院系ID
     * @return
     */
    @RequestMapping(value = "/getMajors", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getStuAcademies(@RequestParam("callback") String callback, @RequestParam("academyId") Integer academyId){
        List<StuMajor> stuMajorList = stuMajorService.selectByAcademyId(academyId);
        String json = JsonUtils.objectToJson(stuMajorList);
        if(callback != null){
            return callback+"("+ json +")";
        }
        return json;
    }
}
