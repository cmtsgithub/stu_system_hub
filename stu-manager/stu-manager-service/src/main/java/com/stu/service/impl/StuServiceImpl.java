package com.stu.service.impl;

import com.stu.mapper.StuBaseMsgMapper;
import com.stu.mapper.StuStudyMsgMapper;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuBaseMsgMapper stuBaseMsgMapper;

    @Autowired
    private StuStudyMsgMapper stuStudyMsgMapper;

    /**
     * 查询所有学生
     * @return 返回一个StuBaseMsg列表
     */
    @Override
    public List<StuBaseMsg> selectStuBaseMsgAll() {
        return stuBaseMsgMapper.selectAll();
    }

    /**
     * 新增学生
     * @param stuBaseMsg pojo
     * @param stuStudyMsg pojo
     * @return 1 新增成功 0 新增失败
     */
    @Override
    @Transactional
    public int stuAdd(StuBaseMsg stuBaseMsg, StuStudyMsg stuStudyMsg) {
        //完善stuBaseMsg信息
        //1.生成学号信息

        //2.创建日期
        //3.修改日期
        //4.初始密码（证件号码后6位）
        return 0;
    }
}
