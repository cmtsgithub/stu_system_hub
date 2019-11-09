package com.stu.controller;

import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.BeanMapUtils;
import com.stu.utils.DateUtils;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 获取所有学生基本信息 StuBaseMsg
     * @param model model
     * @return jsp页面
     */
    @RequestMapping("/getStuBaseMsg")
    public String getStuBaseMsg(Model model){
        List<StuBaseMsg> stuBaseMsgList = stuService.selectStuBaseMsgAll();
        model.addAttribute("stuBaseMsgList", stuBaseMsgList);
        return "student/student";
    }

    /**
     * 新增学生
     * @return JsonResult
     */
    @RequestMapping(value = "/stu_add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult stuAdd(@RequestBody Map<String, Object> map){
        //传递学生对象给服务层
        try{
            StuBaseMsg stuBaseMsg = BeanMapUtils.convertMapToBean(StuBaseMsg.class, map);
            StuStudyMsg stuStudyMsg = BeanMapUtils.convertMapToBean(StuStudyMsg.class, map);
            /*
            完善StubaseMsg对象
             */
            //设置出生日期
            String birthday = (String) map.get("birthday");
            stuBaseMsg.setBirthday(DateUtils.strToDate(birthday));
            //设置入学日期
            String enrollmentDate = (String) map.get("enrollmentDate");
            stuBaseMsg.setEnrollmentDate(DateUtils.strToDate(enrollmentDate));
            //设置学号（学院编号 + 入学年份 + （班级，没有这个属性，用专业编号代替）专业编号 + 专业人数+1）
            String str_academyId = (String) map.get("academyId");
            int academyId = Integer.parseInt(str_academyId);
            String str_majorId = (String) map.get("majorId");
            int majorId = Integer.parseInt(str_majorId);
            String stuId = "";
            //如果院系编号小于10，则在前面补0
            if(academyId < 10){
                stuId = "0" + academyId + enrollmentDate.substring(2, 4);
                //如果专业编号小于10，则在前面补0
                if(majorId < 10){
                    stuId = stuId + "0" + majorId;
                }else {
                    stuId = stuId + majorId;
                }
            }else{
                stuId = academyId + enrollmentDate.substring(2, 4);
                //如果专业编号小于10，则在前面补0
                if(majorId < 10){
                    stuId = stuId + "0" + majorId;
                }else {
                    stuId = stuId + majorId;
                }
            }
            stuBaseMsg.setId(stuId);
            //设置密码（证件号后六位）
            String certificateNumber = stuBaseMsg.getCertificateNumber();
            stuBaseMsg.setPassword(certificateNumber.substring(certificateNumber.length() - 6));
            /*
            设置StuStudyMsg信息
             */
            stuStudyMsg.setAcademyId(academyId);
            stuStudyMsg.setMajorId(majorId);
            //调用service方法
            int result = stuService.stuAdd(stuBaseMsg, stuStudyMsg);
            if(result == 1){
                return JsonResult.ok();
            }else {
                return JsonResult.build(400, "新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "新增失败");
        }
    }
}

