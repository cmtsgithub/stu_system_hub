package com.stu.service;

import com.stu.utils.JsonResult;

public interface AdminLoginService {

    JsonResult login(String name, String password);
}
