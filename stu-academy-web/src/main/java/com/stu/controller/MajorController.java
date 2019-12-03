package com.stu.controller;

import com.stu.pojo.StuMajor;
import com.stu.service.StuMajorService;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据专业编号，返回一个专业对象
     * @param majorId 专业编号
     * @param callback 回调函数
     * @return String 回调函数 包含 专业对象
     */
    @ResponseBody
    @RequestMapping(value = "/getStuMajor", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
    public String getStuAcademy(@RequestParam Integer majorId, @RequestParam("callback") String callback) {
        StuMajor stuMajor = stuMajorService.selectByPrimaryKey(majorId);
        if(stuMajor != null){
            String json = JsonUtils.objectToJson(stuMajor);
            if(callback != null){
                return callback+"("+ json +")";
            }
            return json;
        }
        return null;
    }
}
