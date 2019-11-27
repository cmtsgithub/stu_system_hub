package com.stu.service;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.utils.JsonResult;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface StuService {

    List<StuBaseMsg> selectStuBaseMsgAll();

    int stuAdd(StuBaseMsg stuBaseMsg, StuStudyMsg stuStudyMsg);

    StuBaseMsg selectByPrimaryKey(String id);

    PageInfo selectStuBaseMsgByPageNum(int pageNum, int pageSize, int status);

    int updateStuStudyStatus(String id, Integer status);

    List<StuBaseMsg> selectByStatus(Integer status);

    StuStudyMsg selectByStuId(String id);

    int updateStuBaseMsgByPrimaryKeySelective(StuBaseMsg stuBaseMsg);

    int updateStuStudyMsgByPrimaryKeySelective(StuStudyMsg stuStudyMsg);

    int importStuMsg(File xlsFile);

}
