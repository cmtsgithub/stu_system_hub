package com.stu.mapper;

import com.stu.pojo.StuStudyMsg;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuStudyMsgMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuStudyMsg record);

    int insertSelective(StuStudyMsg record);

    StuStudyMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuStudyMsg record);

    int updateByPrimaryKey(StuStudyMsg record);
}