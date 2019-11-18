package com.stu.mapper;

import com.stu.pojo.StuBaseMsg;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuBaseMsgMapper {

    int deleteByPrimaryKey(String id);

    int insert(StuBaseMsg record);

    int insertSelective(StuBaseMsg record);

    StuBaseMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StuBaseMsg record);

    int updateByPrimaryKey(StuBaseMsg record);

    List<StuBaseMsg> selectAll();

    List<StuBaseMsg> selectByStatus(Integer status);
}