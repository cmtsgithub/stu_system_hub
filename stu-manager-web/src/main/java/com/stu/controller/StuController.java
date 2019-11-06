package com.stu.controller;

import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    @RequestMapping("/getStuBaseMsg")
    public String getStuBaseMsg(Model model){
        List<StuBaseMsg> stuBaseMsgList = stuService.selectStuBaseMsgAll();
        model.addAttribute("stuBaseMsgList", stuBaseMsgList);
        return "student/student";
    }

    /**
     * 新增学生
     * @param stuBaseMsg 映射pojo对象
     * @param stuStudyMsg 映射pojo对象
     * @return JsonResult
     */
    @RequestMapping(value = "/stu_base_msg_add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult stuBaseMsgAdd(@RequestBody StuBaseMsg stuBaseMsg, @RequestBody StuStudyMsg stuStudyMsg){
        //传递学生对象给服务层

        return JsonResult.ok();
    }


}

