package com.stu.controller;

import com.github.pagehelper.PageInfo;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 获取所有学生基本信息 StuBaseMsg
     * @param status 学籍状态， 默认为1
     * @param pageNum 当前页 默认为1
     * @param pageSize 页面最大容量 默认为8
     * @return
     */
    @RequestMapping("/getStuBaseMsg")
    @ResponseBody
    public String getStuBaseMsg(@RequestParam(defaultValue = "1") Integer status,
                                @RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize){
        //获得PageInfo
        PageInfo pageInfo = stuService.selectStuBaseMsgByPageNum(pageNum, pageSize, status);
        //把pageInfo转换为Json对象
        String json = JsonUtils.objectToJson(pageInfo);
        return json;
    }

    /**
     * 通过名字查找学生
     * @param name 名字
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return json字符串
     */
    @RequestMapping(value = "/getStuBaseMsgByName", method = RequestMethod.GET)
    @ResponseBody
    public String getStuBaseMsgByName(@RequestParam(defaultValue = "") String name,
                                      @RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize){
        try{
            name = new String(name.getBytes("ISO8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageInfo<StuBaseMsg> pageInfo = stuService.selectByNameByPageNum(pageNum, pageSize, name);
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
            //获取身份证后6位
            String substringPassword = certificateNumber.substring(certificateNumber.length() - 6);
            //MD5加密
            String password = MD5Utils.stringToMD5(substringPassword);
            stuBaseMsg.setPassword(password);
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

    /**
     * 更新学生基本信息
     * @param stuBaseMsg 映射对象
     * @return Json
     */
    @ResponseBody
    @RequestMapping(value = "/update/stuBaseMsg", method = RequestMethod.POST)
    public JsonResult updateStuBaseMsg(@RequestBody StuBaseMsg stuBaseMsg){
        //调用服务层方法
        int result = stuService.updateStuBaseMsgByPrimaryKeySelective(stuBaseMsg);
        //返回Json对象
        if(result == 1)
            return JsonResult.ok();
        else if(result == -1)
            return JsonResult.build(400, "更新失败, 请获取最新的学生信息");
        else
            return JsonResult.build(400, "更新失败，原因未知，请重试");
    }

    /**
     * 更新学生学籍信息
     * @param stuStudyMsg 映射对象
     * @return Json
     */
    @ResponseBody
    @RequestMapping(value = "/update/stuStudyMsg", method = RequestMethod.POST)
    public JsonResult updateStuStudyMsg(@RequestBody StuStudyMsg stuStudyMsg){
        //调用服务层方法
        int result = stuService.updateStuStudyMsgByPrimaryKeySelective(stuStudyMsg);
        //返回Json对象
        if(result == 1){
            return JsonResult.ok();
        } else if (result == -1){
            return JsonResult.build(400, "学籍信息已过期，请重新获取");
        } else {
            return JsonResult.build(400, "更新失败，原因未知，请重试");
        }
    }

    /**
     * 通过excel导入学生数据
     * @return Json
     */
    @RequestMapping(value = "/upload/file/uploadXls", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult importStu(MultipartFile multipartFile){
        File file = null;
        try {
            // 获取输入流
            InputStream inputStream = multipartFile.getInputStream();
            // 取出文件的扩展名
            String originalFilename = multipartFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if(!ext.equals("xls"))
                return JsonResult.build(400, "请选择正确的excel文件");
            //输入流转File
            file = FileUtils.inputStreamToFile(inputStream);
            //调用服务层方法
            int result = stuService.importStuMsg(file);
            if(result == 1)
                return JsonResult.build(200, "数据导入成功");
            else return JsonResult.build(400, "数据导入失败");
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.build(400, "数据导入失败");
        }finally {
            //调用完成后删除文件
            file.delete();
        }
    }

    /**
     * 导出学生数据到excel
     * @param response response
     * @return 学生列表页面
     */
    @RequestMapping("/stu/exportExcel")
    @ResponseBody
    public Map<Object, String> exportStuExcel(HttpServletResponse response){
        response.setContentType("application/binary;charset=UTF-8");
        try{
            try {
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：stu.xls)
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("stu.xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            //创建Excel实体类对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] headers_1 = { "学号", "姓名", "性别", "证件类型", "证件号", "生日",
                    "民族", "政治面貌", "头像地址URL", "祖籍", "入学日期", "户口所在地", "信息创建日期", "信息更新日期", "登录密码" };
            List<StuBaseMsg> stuBaseMsgList = stuService.selectStuBaseMsgAll();
            ExcelUtils.exportExcel("学生基本信息", headers_1, stuBaseMsgList, "yyyy-MM-dd HH:mm:ss", workbook);
            String[] headers_2 = {"编号", "学号", "学年", "学期", "院系代码", "专业代码", "学籍状态", "是否在校", "教育水平", "文化水平", "学生类别", "信息更新日期"};
            List<StuStudyMsg> stuStudyMsgList = stuService.selectStuStudyMsgAll();
            ExcelUtils.exportExcel("学生学籍信息", headers_2, stuStudyMsgList, "yyyy-MM-dd HH:mm:ss", workbook);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return null;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

