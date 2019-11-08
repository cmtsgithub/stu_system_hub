package com.stu.controller;

import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.BeanMapUtils;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

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
     * @return JsonResult
     */
    @RequestMapping(value = "/stu_add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult stuAdd(@RequestBody Map<String, Object> map){
        //传递学生对象给服务层
        try{
            StuBaseMsg stuBaseMsg = BeanMapUtils.convertMapToBean(StuBaseMsg.class, map);
            StuStudyMsg stuStudyMsg = BeanMapUtils.convertMapToBean(StuStudyMsg.class, map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "json转换失败");
        }
        return JsonResult.ok();
    }


}

