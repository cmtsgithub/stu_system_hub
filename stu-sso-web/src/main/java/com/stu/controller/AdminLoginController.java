package com.stu.controller;

import com.stu.pojo.StuAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员登陆控制器
 */
@Controller
public class AdminLoginController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String login(StuAdmin admin){
        System.out.println(admin.toString());
        return "您已成功登陆";
    }
}
