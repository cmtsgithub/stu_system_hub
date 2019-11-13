package com.stu.controller;

import com.github.pagehelper.PageInfo;
import com.stu.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String stuBaseMsgPage(Model model, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize){
        //获得PageInfo
        PageInfo pageInfo = stuService.selectStuBaseMsgByPageNum(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student";
    }

}
