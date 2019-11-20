package com.stu.service;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.utils.JsonResult;

import java.util.List;

public interface StuService {

    List<StuBaseMsg> selectStuBaseMsgAll();

    int stuAdd(StuBaseMsg stuBaseMsg, StuStudyMsg stuStudyMsg);

    StuBaseMsg selectByPrimaryKey(String id);

    PageInfo selectStuBaseMsgByPageNum(int pageNum, int pageSize, int status);

    int updateStuStudyStatus(String id, Integer status);

    List<StuBaseMsg> selectByStatus(Integer status);

    StuStudyMsg selectByStuId(String id);

    int updateStuBaseMsgByPrimaryKeySelective(StuBaseMsg stuBaseMsg);

}
