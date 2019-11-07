package com.stu.service;

import com.stu.pojo.StuMajor;

import java.util.List;

public interface StuMajorService {

    List<StuMajor> selectByAcademyId(Integer id);
}
