package com.stu.service.impl;

import com.stu.service.StuAcademyService;
import com.stu.mapper.StuAcademyMapper;
import com.stu.pojo.StuAcademy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuAcademyServiceImpl implements StuAcademyService {

    @Autowired
    private StuAcademyMapper stuAcademyMapper;

    /**
     * 查询所有学院信息类对象
     * @return List<StuAcademy>
     */
    @Override
    public List<StuAcademy> selectAll() {
        return stuAcademyMapper.selectAll();
    }
}
