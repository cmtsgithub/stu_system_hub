package com.stu.mapper;

import com.stu.pojo.StuAcademy;

import java.util.List;

public interface StuAcademyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuAcademy record);

    int insertSelective(StuAcademy record);

    StuAcademy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuAcademy record);

    int updateByPrimaryKey(StuAcademy record);

    List<StuAcademy> selectAll();
}