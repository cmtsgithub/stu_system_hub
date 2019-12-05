package com.stu.service;

import com.stu.utils.JsonResult;

public interface AdminLoginService {

    JsonResult login(String name, String password);

    JsonResult getUserByToken(String token);

    JsonResult logout(String token);
}
