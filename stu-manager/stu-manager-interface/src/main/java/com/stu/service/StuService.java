package com.stu.service;

import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.utils.JsonResult;

import java.util.List;

public interface StuService {

    List<StuBaseMsg> selectStuBaseMsgAll();

    int stuAdd(StuBaseMsg stuBaseMsg, StuStudyMsg stuStudyMsg);

    StuBaseMsg selectByPrimaryKey(String id);

}
