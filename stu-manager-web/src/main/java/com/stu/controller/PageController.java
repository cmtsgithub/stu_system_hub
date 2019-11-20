package com.stu.controller;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 页面跳转类
 */
@Controller
public class PageController {

    @Autowired
    private StuService stuService;

    /**
     * 跳转到学生列表页面
     * @return
     */
    @RequestMapping(value = "/stuBaseMsgPage", method = RequestMethod.GET)
    public String stuBaseMsgPage(Model model, @RequestParam(defaultValue = "1") Integer status, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize){
        //获得PageInfo
        PageInfo pageInfo = stuService.selectStuBaseMsgByPageNum(pageNum, pageSize, status);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student";
    }

    /**
     * 跳转到学生信息更新页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable String id){
        //查询学生id
        StuBaseMsg stuBaseMsg = stuService.selectByPrimaryKey(id);
        if(stuBaseMsg == null)
            //返回错误页面
            return null;
        //若学生id存在，则进行下列操作
        //查询StuBaseMsg
        //查询StuStudyMsg
        StuStudyMsg stuStudyMsg = stuService.selectByStuId(id);
        //存入Model中
        model.addAttribute("stuBaseMsg", stuBaseMsg);
        model.addAttribute("stuStudyMsg", stuStudyMsg);
        //返回update页面
        return "student/update";
    }

}
