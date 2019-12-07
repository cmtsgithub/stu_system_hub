package com.stu.interceptor;

import com.stu.pojo.StuAdmin;
import com.stu.utils.CookieUtils;
import com.stu.utils.HttpClientUtil;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆拦截器 （暂时只实现了对管理员页面进行拦截）
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    private String SSO_PAGE_LOGIN;
    @Value("${COOKIE_TOKEN_KEY}")
    private String COOKIE_TOKEN_KEY;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //从cookie中取出token
        String token = CookieUtils.getCookieValue(httpServletRequest, COOKIE_TOKEN_KEY);
        //判断cookie是否为空
        if(StringUtils.isEmpty(token)){
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            String redirectUrl = SSO_BASE_URL + SSO_PAGE_LOGIN
                    + "?redirect=" + httpServletRequest.getRequestURL() + "&errorCode=12138";
            httpServletResponse.sendRedirect(redirectUrl);
            return false;
        }
        //查询sso系统
        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
        //判断返回的用户对象是否为空
        if (!StringUtils.isEmpty(json)) {
            JsonResult result = JsonResult.formatToPojo(json, StuAdmin.class);
            if (result.getStatus() == 200) {
                return true;
            }else {
            }
        }else{
        }
        //总结异常
        //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
        String redirectUrl = SSO_BASE_URL + SSO_PAGE_LOGIN
                + "?redirect=" + httpServletRequest.getRequestURL() + "&errorCode=12138";
        httpServletResponse.sendRedirect(redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
