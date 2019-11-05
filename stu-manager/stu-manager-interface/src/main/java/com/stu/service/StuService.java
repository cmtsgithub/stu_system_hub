package com.stu.service;

import com.stu.pojo.StuBaseMsg;
import com.stu.utils.JsonResult;

import java.util.List;

public interface StuService {

    List<StuBaseMsg> selectStuBaseMsgAll();

    JsonResult insertSelective(StuBaseMsg record);
}
