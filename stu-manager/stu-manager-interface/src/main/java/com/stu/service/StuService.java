package com.stu.service;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.util.Collection;
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

    int updateStuStudyMsgByPrimaryKeySelective(StuStudyMsg stuStudyMsg);

    int importStuMsg(File xlsFile);

    int exportStuMsg(String title, String[] headers, Collection dataset, String pattern, HSSFWorkbook workbook);

    List<StuStudyMsg> selectStuStudyMsgAll();

    List<StuBaseMsg> selectByName(String name);

    PageInfo<StuBaseMsg> selectByNameByPageNum(int pageNum, int pageSize, String name);

}
