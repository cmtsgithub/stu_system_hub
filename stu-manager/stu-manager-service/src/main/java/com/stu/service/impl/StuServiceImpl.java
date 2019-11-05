package com.stu.service.impl;

import com.stu.mapper.StuBaseMsgMapper;
import com.stu.pojo.StuBaseMsg;
import com.stu.service.StuService;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuBaseMsgMapper stuBaseMsgMapper;

    @Override
    public List<StuBaseMsg> selectStuBaseMsgAll() {
        return stuBaseMsgMapper.selectAll();
    }

    @Override
    public JsonResult insertSelective(StuBaseMsg record) {
        //从长计议
        return null;
    }
}
