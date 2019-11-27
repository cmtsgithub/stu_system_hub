package com.stu;

import static org.junit.Assert.assertTrue;

import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuMajor;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuService;
import com.stu.service.impl.StuServiceImpl;
import com.stu.utils.DateUtils;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.bcel.generic.DADD;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    StuService stuService = null;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        StuService bean = applicationContext.getBean(StuService.class);
        stuService = bean;
    }


//    @Test
//    public void excelCovertSql(){
//        try {
//            Workbook workbook= Workbook.getWorkbook(new File("C:\\Users\\陈铭涛\\Desktop\\stu.xls"));
//            Sheet sheet=workbook.getSheet(0);//表
//            int clos=sheet.getColumns();//得到所有的列
//            int rows=sheet.getRows();//得到所有的行
//            int num = 0;
//            for (int i = 1; i < rows; i++) {
//                for (int j = 0; j < clos; j++) {
//                    //第一个是列数，第二个是行数
//                    String name = sheet.getCell(j++, i).getContents();
//                    String sex = sheet.getCell(j++, i).getContents();
//                    String certificate_type = sheet.getCell(j++, i).getContents();
//                    String certificate_number = sheet.getCell(j++, i).getContents();
//                    String birthday = sheet.getCell(j++, i).getContents();
//                    String nation = sheet.getCell(j++, i).getContents();
//                    String politics_status = sheet.getCell(j++, i).getContents();
//                    String image = sheet.getCell(j++, i).getContents();
//                    String ancestral_home = sheet.getCell(j++, i).getContents();
//                    String enrollment_date = sheet.getCell(j++, i).getContents();
//                    String reg_permanent_residence = sheet.getCell(j++, i).getContents();
//                    String semester = sheet.getCell(j++, i).getContents();
//                    String term = sheet.getCell(j++, i).getContents();
//                    String academy_id = sheet.getCell(j++, i).getContents();
//                    String major_id = sheet.getCell(j++, i).getContents();
//                    String study_status = sheet.getCell(j++, i).getContents();
//                    String is_in_school = sheet.getCell(j++, i).getContents();
//                    String education_level = sheet.getCell(j++, i).getContents();
//                    String cultivate_level = sheet.getCell(j++, i).getContents();
//                    String category = sheet.getCell(j++, i).getContents();
//
//                    //判断空字段
//                    if(StringUtils.isEmpty(name))
//                        continue;
//                    if(StringUtils.isEmpty(sex))
//                        continue;
//                    if(StringUtils.isEmpty(certificate_type))
//                        continue;
//                    if(StringUtils.isEmpty(certificate_number))
//                        continue;
//                    if(StringUtils.isEmpty(birthday))
//                        continue;
//                    if(StringUtils.isEmpty(academy_id))
//                        continue;
//                    if(StringUtils.isEmpty(major_id))
//                        continue;
//                    if(StringUtils.isEmpty(enrollment_date))
//                        continue;
//                    //生成学号
//                    String stuId = creatStuId(academy_id, major_id, enrollment_date, num++);
//                    //创建对象
//                    StuBaseMsg stuBaseMsg = new StuBaseMsg();
//                    stuBaseMsg.setId(stuId);
//                    stuBaseMsg.setName(name);
//                    stuBaseMsg.setSex(sex);
//                    stuBaseMsg.setCertificateType(certificate_type);
//                    stuBaseMsg.setCertificateNumber(certificate_number);
//                    stuBaseMsg.setBirthday(DateUtils.strToDate(birthday));
//                    stuBaseMsg.setNation(nation);
//                    stuBaseMsg.setPoliticsStatus(politics_status);
//                    stuBaseMsg.setImage(image);
//                    stuBaseMsg.setAncestralHome(ancestral_home);
//                    stuBaseMsg.setEnrollmentDate(DateUtils.strToDate(enrollment_date));
//                    stuBaseMsg.setRegPermanentResidence(reg_permanent_residence);
//                    stuBaseMsg.setPassword(certificate_number.substring(certificate_number.length() - 6));
//                    stuBaseMsg.setCreated(DateUtils.getNow());
//                    stuBaseMsg.setUpdated(DateUtils.getNow());
//
//                    //创建学生学籍对象
//                    StuStudyMsg stuStudyMsg = new StuStudyMsg();
//                    stuStudyMsg.setStuId(stuId);
//                    stuStudyMsg.setAcademyId(Integer.parseInt(academy_id));
//                    stuStudyMsg.setMajorId(Integer.parseInt(major_id));
//                    stuStudyMsg.setSemester(semester);
//                    if(!StringUtils.isEmpty(term))
//                        stuStudyMsg.setTerm(Integer.parseInt(term));
//                    if(!StringUtils.isEmpty(study_status))
//                        stuStudyMsg.setStudyStatus(Integer.parseInt(study_status));
//                    if(!StringUtils.isEmpty(is_in_school))
//                        stuStudyMsg.setIsInSchool(Integer.parseInt(is_in_school));
//                    stuStudyMsg.setCultivateLevel(cultivate_level);
//                    stuStudyMsg.setEducationLevel(education_level);
//                    stuStudyMsg.setCategory(category);
//                    stuStudyMsg.setUpdated(DateUtils.getNow());
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private String creatStuId(String academy_id_str, String major_id_str, String enrollmentDate, Integer num){
//        //如果院系编号小于10，则在前面补0
//        String stuId = "";
//        //判断空串
//        if(StringUtils.isEmpty(academy_id_str))
//            return null;
//        if(StringUtils.isEmpty(major_id_str))
//            return null;
//        if(StringUtils.isEmpty(enrollmentDate))
//            return null;
//        if(num < 0)
//            return null;
//        //类型转换
//        int academyId = Integer.parseInt(academy_id_str);
//        int majorId = Integer.parseInt(major_id_str);
//        //生成学号
//        if(academyId < 10){
//            stuId = "0" + academyId + enrollmentDate.substring(2, 4);
//            //如果专业编号小于10，则在前面补0
//            if(majorId < 10){
//                stuId = stuId + "0" + major_id_str;
//            }else {
//                stuId = stuId + major_id_str;
//            }
//        }else{
//            stuId = academyId + enrollmentDate.substring(2, 4);
//            //如果专业编号小于10，则在前面补0
//            if(majorId < 10){
//                stuId = stuId + "0" + major_id_str;
//            }else {
//                stuId = stuId + major_id_str;
//            }
//        }
//        if(num == null){
//            num = 0;
//        }
//        if(num > 99){
//            //专业人数过多
//            return null;
//        }else{
//            //专业人数+1
//            num++;
//            if(num < 10){
//                //补齐学号信息
//                stuId = stuId + "0" + num;
//            }else{
//                stuId = stuId + num;
//            }
//        }
//        return stuId;
//    }
//
//    @Test
//    public void testImportStu(){
//        int result = stuService.importStuMsg(new File("C:\\Users\\陈铭涛\\Desktop\\stu.xls"));
//        System.out.println(result);
//    }

}
