package com.stu.controller;

import com.stu.pojo.StuAdmin;
import com.stu.service.AdminLoginService;
import com.stu.utils.CookieUtils;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员登陆控制器
 */
@Controller
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Value("${COOKIE_TOKEN_KEY}")
    private String COOKIE_TOKEN_KEY;

    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;


    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String name, String password, HttpServletRequest request, HttpServletResponse response){
        JsonResult result = adminLoginService.login(name, password);
        //判断用户登陆情况
        if(result.getStatus() != 200){
            return result;
        }
        //取出token
        String token = result.getData().toString();
        // 在返回结果之前，设置cookie(即将token写入cookie)
        // 1.cookie跨域
        // 2.设置cookie的有效期
        CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, token, COOKIE_EXPIRE);
        // 返回结果
        return result;
    }
}
