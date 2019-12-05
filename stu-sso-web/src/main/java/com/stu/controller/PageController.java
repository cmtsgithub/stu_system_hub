package com.stu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员登陆页面跳转类
 */
@Controller
public class PageController {

    /**
     * 跳转到登陆页面
     * @return jsp路径
     */
    @RequestMapping(value = "/admin/login/page", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
}
