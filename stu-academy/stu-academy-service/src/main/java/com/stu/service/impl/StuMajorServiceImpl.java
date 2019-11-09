package com.stu.service.impl;

import com.stu.mapper.StuMajorMapper;
import com.stu.pojo.StuMajor;
import com.stu.service.StuMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuMajorServiceImpl implements StuMajorService {

    @Autowired
    private StuMajorMapper stuMajorMapper;

    @Override
    public List<StuMajor> selectByAcademyId(Integer id) {
        List<StuMajor> stuMajors = stuMajorMapper.selectByAcademyId(id);
        return stuMajors;
    }

    @Override
    public StuMajor selectByPrimaryKey(Integer id) {
        return stuMajorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StuMajor record) {
        return stuMajorMapper.updateByPrimaryKeySelective(record);
    }
}
