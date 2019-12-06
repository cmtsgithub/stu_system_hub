package com.stu.controller;

import com.stu.pojo.StuAdmin;
import com.stu.service.AdminLoginService;
import com.stu.utils.CookieUtils;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    /**
     * 用户登陆
     * @param name 用户名
     * @param password 密码
     * @param request request
     * @param response response
     * @return JsonResult
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String name, String password, String redirect, HttpServletRequest request, HttpServletResponse response){
        JsonResult result = adminLoginService.login(name, password);
        //判断用户登陆情况
        if(result.getStatus() != 200){
            return result;
        }
        //取出token
        Map<String, String> map = (Map<String, String>) result.getData();
        String token = map.get("token");
        // 在返回结果之前，设置cookie(即将token写入cookie)
        // 1.cookie跨域
        // 2.设置cookie的有效期
        CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, token, COOKIE_EXPIRE);
        //判断愿页面url是否为空
        if(!org.springframework.util.StringUtils.isEmpty(redirect)){
            //设置回调url
            map.put("redirect", redirect);
        }
        // 返回结果
        return result;
    }

    /**
     * 获取用户登陆信息
     * @param token token
     * @param callback 回调函数
     * @return 拼接的json字符串
     */
    @RequestMapping(value="/user/token/{token}", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getUserByToken(@PathVariable String token, String callback) {
        JsonResult result = adminLoginService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)) {
            // 客户端为jsonp请求，需要返回js代码
            String jsonResult = callback + "(" + JsonUtils.objectToJson(result) + ");";
            return jsonResult; // 统一返回字符串
        }
        return JsonUtils.objectToJson(result); // 统一返回字符串
    }

    /**
     * 用户登出
     * @param token token
     * @param callback 回调函数
     * @param request request
     * @param response response
     * @return 拼接的json字符串
     */
    @RequestMapping(value="/user/logout/{token}", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String logout(@PathVariable String token, String callback,
                         HttpServletRequest request, HttpServletResponse response) {
        //调用服务层方法
        JsonResult result = adminLoginService.logout(token);
        //设置cookie过期
        CookieUtils.deleteCookie(request, response, COOKIE_TOKEN_KEY);
        //返回jsonp字符串
        if (StringUtils.isNotBlank(callback)) {
            // 客户端为jsonp请求，需要返回js代码
            String jsonResult = callback + "(" + JsonUtils.objectToJson(result) + ");";
            return jsonResult; // 统一返回字符串
        }
        return JsonUtils.objectToJson(result); // 统一返回字符串
    }
}
