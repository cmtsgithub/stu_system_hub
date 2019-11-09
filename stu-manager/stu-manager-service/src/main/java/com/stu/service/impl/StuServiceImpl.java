package com.stu.service.impl;

import com.stu.mapper.StuBaseMsgMapper;
import com.stu.mapper.StuStudyMsgMapper;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuMajor;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuMajorService;
import com.stu.service.StuService;
import com.stu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuBaseMsgMapper stuBaseMsgMapper;

    @Autowired
    private StuStudyMsgMapper stuStudyMsgMapper;

    @Autowired
    private StuMajorService stuMajorService;

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
        /*
        完善学号信息
         */
        String id = stuBaseMsg.getId();
        /*
        乐观锁
         */
        //查询专业人数，记录专业信息修改时间
        StuMajor stuMajor = stuMajorService.selectByPrimaryKey(stuStudyMsg.getMajorId());
        Integer number = stuMajor.getNumber();
        if(number == null){
            //mabatis 对于 Integer为0的值为误判为null
            number = 0;
        }
        if(number > 99){
            //专业人数过多
            return 0;
        }else{
            //专业人数+1
            number++;
            if(number < 10){
                //补齐学号信息
                id = id + "0" + number;
            }else{
                id = id + number;
            }
        }
        Date updated = stuMajor.getUpdated();
        stuBaseMsg.setId(id);
        stuStudyMsg.setStuId(id);
        //比对专业信息修改时间
        Date updated_2 = stuMajorService.selectByPrimaryKey(stuStudyMsg.getMajorId()).getUpdated();
        //如果两个时间相等，则提交事务
        if(DateUtils.dateToStrLong(updated).equals(DateUtils.dateToStrLong(updated_2))){
            //时间符合，提交事务
            stuMajor.setNumber(number);
            stuMajor.setUpdated(DateUtils.getNow());
            stuMajorService.updateByPrimaryKeySelective(stuMajor);
            stuBaseMsgMapper.insertSelective(stuBaseMsg);
            stuStudyMsgMapper.insertSelective(stuStudyMsg);
            return 1;
        }else{
            return 0;
        }
    }
}
