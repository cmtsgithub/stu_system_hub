package com.stu.controller;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.BeanMapUtils;
import com.stu.utils.DateUtils;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 获取所有学生基本信息 StuBaseMsg
     * @param pageNum 当前页
     * @param pageSize 页面最大容量
     * @return
     */
    @RequestMapping("/getStuBaseMsg")
    @ResponseBody
    public String getStuBaseMsg(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize){
        //获得PageInfo
        PageInfo pageInfo = stuService.selectStuBaseMsgByPageNum(pageNum, pageSize);
        //把pageInfo转换为Json对象
        String json = JsonUtils.objectToJson(pageInfo);
        return json;
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
            if(result == 0){
                return JsonResult.build(400, "新增失败，请重试");
            }else{
                //把id转换为字符串
                String id = String.valueOf(result);
                //判断字符串长度，如果是7位的话前面需要补0
                if(id.length()==7){
                    id = "0" + id;
                }
                //把新增的对象返回回去
                StuBaseMsg add_StuBaseMsg = stuService.selectByPrimaryKey(id);
                return JsonResult.build(200, "新增成功", add_StuBaseMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "新增失败");
        }
    }

    /**
     * 更改学生状态
     * @param id 学号
     * @param status 1为在读 2为毕业 3为退学
     * @return JsonResult
     */
    @RequestMapping(value = "/stuStatus/{id}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateStuStatus(@PathVariable String id, @PathVariable Integer status){
        //查看此Id是否存在
        //id补0
        if(id.length() == 7){
            id = "0" + id;
        }
        StuBaseMsg stuBaseMsg = stuService.selectByPrimaryKey(id);
        if(stuBaseMsg == null){
            return JsonResult.build(400, "学号不存在!");
        }
        //若存在，调用更新学籍状态更新方法
        int result = stuService.updateStuStudyStatus(id, status);
        //接受更新结果
        if(result == 1){
            //返回Json对象
            return JsonResult.ok();
        }else{
            return JsonResult.build(400, "更新失败，请重试!");
        }
    }
}

