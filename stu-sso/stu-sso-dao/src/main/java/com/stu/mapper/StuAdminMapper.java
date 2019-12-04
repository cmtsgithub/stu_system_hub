package com.stu.mapper;

import com.stu.pojo.StuAdmin;
import com.stu.pojo.StuAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuAdminMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuAdmin record);

    int insertSelective(StuAdmin record);

    StuAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuAdmin record);

    int updateByPrimaryKey(StuAdmin record);
}