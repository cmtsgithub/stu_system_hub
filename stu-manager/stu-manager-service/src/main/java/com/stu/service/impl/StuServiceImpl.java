package com.stu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.mapper.StuBaseMsgMapper;
import com.stu.mapper.StuStudyMsgMapper;
import com.stu.pojo.StuBaseMsg;
import com.stu.pojo.StuMajor;
import com.stu.pojo.StuStudyMsg;
import com.stu.service.StuMajorService;
import com.stu.service.StuService;
import com.stu.utils.DateUtils;
import com.stu.utils.ExcelUtils;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

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
     * @return 学号 新增成功 0 新增失败
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
            return Integer.parseInt(stuBaseMsg.getId());
        }else{
            return 0;
        }
    }

    /**
     * 返回一个StuBaseMsg对象
     * @param id 学号，主键
     * @return StuBaseMsg
     */
    @Override
    public StuBaseMsg selectByPrimaryKey(String id) {
        return stuBaseMsgMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询学生信息带分页显示
     * @param pageNum 当前页码
     * @param pageSize 页面最大容量
     * @param status 学籍状态
     * @return PageInfo
     */
    @Override
    public PageInfo selectStuBaseMsgByPageNum(int pageNum, int pageSize, int status) {
        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        //获得学生列表
        List<StuBaseMsg> stuBaseMsgList = selectByStatus(status);
        //设置PageInfo
        PageInfo<StuBaseMsg> pageInfo = new PageInfo<>(stuBaseMsgList);
        return pageInfo;
    }

    /**
     * 更新学生学籍状态
     * @param id 学号
     * @param status 1为在读 2为毕业 3为退学
     * @return
     */
    @Override
    public int updateStuStudyStatus(String id, Integer status) {
        //获取该id对应的StuStudyMsg对象
        StuStudyMsg stuStudyMsg = stuStudyMsgMapper.selectByStuId(id);
        //改变StuStudyMsg对象中的study_status属性 ，改为3  3为退学状态
        stuStudyMsg.setStudyStatus(status);
        //修改StuStudyMsg的update属性
        stuStudyMsg.setUpdated(DateUtils.getNow());
        //提交
        int result = stuStudyMsgMapper.updateByPrimaryKeySelective(stuStudyMsg);
        //修改成功返回 1， 修改失败返回 0
        return result;
    }

    /**
     * 通过学籍状态来查询学生
     * @param status 学籍状态
     * @return
     */
    @Override
    public List<StuBaseMsg> selectByStatus(Integer status) {
        return stuBaseMsgMapper.selectByStatus(status);
    }

    /**
     * 通过学号来查询学生的学籍信息
     * @param id 学号
     * @return StuStudyMsg
     */
    @Override
    public StuStudyMsg selectByStuId(String id) {
        return stuStudyMsgMapper.selectByStuId(id);
    }

    /**
     * 更新StuBaseMsg对象
     * @param stuBaseMsg 需要更新的对象
     * @return  1 更新成功   0 更新失败   -1 当前更新对象已过期
     */
    @Override
    public int updateStuBaseMsgByPrimaryKeySelective(StuBaseMsg stuBaseMsg) {
        //获取id信息
        String id = stuBaseMsg.getId();
        //从数据库中查询最新的StuBaseMsg对象
        StuBaseMsg record = stuBaseMsgMapper.selectByPrimaryKey(id);
        //获取update时间
        String record_updated = DateUtils.dateToStrLong(record.getUpdated());
        String page_update = DateUtils.dateToStrLong(stuBaseMsg.getUpdated());
        //比对update时间
        if(record_updated.equals(page_update)){
            //获取当前时间，并更新最新更新时间
            stuBaseMsg.setUpdated(DateUtils.getNow());
            //调用Mapper层方法
            int result = stuBaseMsgMapper.updateByPrimaryKeySelective(stuBaseMsg);
            return result;
        }
        //如果时间不相等
        else{
            return -1;
        }
    }

    /**
     * 更新学生学籍信息
     * @param stuStudyMsg 对象
     * @return 1 更新成功 0 更新失败 -1 当前更新对象已过期
     */
    @Override
    public int updateStuStudyMsgByPrimaryKeySelective(StuStudyMsg stuStudyMsg) {
        //获取主键
        Integer id = stuStudyMsg.getId();
        //从数据库查询最新对象
        StuStudyMsg record = stuStudyMsgMapper.selectByPrimaryKey(id);
        //比对update
        String record_updated = DateUtils.dateToStrLong(record.getUpdated());
        String page_updated = DateUtils.dateToStrLong(stuStudyMsg.getUpdated());
        //update不相等，返回 -1
        if(!record_updated.equals(page_updated))
            return -1;
        //更新updated时间
        stuStudyMsg.setUpdated(DateUtils.getNow());
        //调用Mapper层方法
        int result = stuStudyMsgMapper.updateByPrimaryKeySelective(stuStudyMsg);
        //更新成功，返回 1  更新失败，返回 0
        return result;
    }

    /**
     * 导入学生数据
     * @param xlsFile excel文件
     * @return 1 导入成功 0 导入失败
     */
    @Override
    public int importStuMsg(File xlsFile) {
        try {
            Workbook workbook= Workbook.getWorkbook(xlsFile);
            Sheet sheet=workbook.getSheet(0);//表
            int clos=sheet.getColumns();//得到所有的列
            int rows=sheet.getRows();//得到所有的行
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String name = sheet.getCell(j++, i).getContents();
                    String sex = sheet.getCell(j++, i).getContents();
                    String certificate_type = sheet.getCell(j++, i).getContents();
                    String certificate_number = sheet.getCell(j++, i).getContents();
                    String birthday = sheet.getCell(j++, i).getContents();
                    String nation = sheet.getCell(j++, i).getContents();
                    String politics_status = sheet.getCell(j++, i).getContents();
                    String image = sheet.getCell(j++, i).getContents();
                    String ancestral_home = sheet.getCell(j++, i).getContents();
                    String enrollment_date = sheet.getCell(j++, i).getContents();
                    String reg_permanent_residence = sheet.getCell(j++, i).getContents();
                    String semester = sheet.getCell(j++, i).getContents();
                    String term = sheet.getCell(j++, i).getContents();
                    String academy_id = sheet.getCell(j++, i).getContents();
                    String major_id = sheet.getCell(j++, i).getContents();
                    String study_status = sheet.getCell(j++, i).getContents();
                    String is_in_school = sheet.getCell(j++, i).getContents();
                    String education_level = sheet.getCell(j++, i).getContents();
                    String cultivate_level = sheet.getCell(j++, i).getContents();
                    String category = sheet.getCell(j++, i).getContents();
                    //判断空字段
                    if(StringUtils.isEmpty(name))
                        continue;
                    if(StringUtils.isEmpty(sex))
                        continue;
                    if(StringUtils.isEmpty(certificate_type))
                        continue;
                    if(StringUtils.isEmpty(certificate_number))
                        continue;
                    if(StringUtils.isEmpty(birthday))
                        continue;
                    if(StringUtils.isEmpty(academy_id))
                        continue;
                    if(StringUtils.isEmpty(major_id))
                        continue;
                    if(StringUtils.isEmpty(enrollment_date))
                        continue;
                    //获取专业人数
                    StuMajor stuMajor = stuMajorService.selectByPrimaryKey(Integer.parseInt(major_id));
                    Integer number = stuMajor.getNumber();
                    //获取专业最近修改时间
                    Date updated_recent = stuMajor.getUpdated();
                    //生成学号
                    String stuId = creatStuId(academy_id, major_id, enrollment_date, number);
                    //创建对象
                    StuBaseMsg stuBaseMsg = new StuBaseMsg();
                    stuBaseMsg.setId(stuId);
                    stuBaseMsg.setName(name);
                    stuBaseMsg.setSex(sex);
                    stuBaseMsg.setCertificateType(certificate_type);
                    stuBaseMsg.setCertificateNumber(certificate_number);
                    stuBaseMsg.setBirthday(DateUtils.strToDate(birthday));
                    stuBaseMsg.setNation(nation);
                    stuBaseMsg.setPoliticsStatus(politics_status);
                    stuBaseMsg.setImage(image);
                    stuBaseMsg.setAncestralHome(ancestral_home);
                    stuBaseMsg.setEnrollmentDate(DateUtils.strToDate(enrollment_date));
                    stuBaseMsg.setRegPermanentResidence(reg_permanent_residence);
                    stuBaseMsg.setPassword(certificate_number.substring(certificate_number.length() - 6));
                    stuBaseMsg.setCreated(DateUtils.getNow());
                    stuBaseMsg.setUpdated(DateUtils.getNow());
                    //创建学生学籍对象
                    StuStudyMsg stuStudyMsg = new StuStudyMsg();
                    stuStudyMsg.setStuId(stuId);
                    stuStudyMsg.setAcademyId(Integer.parseInt(academy_id));
                    stuStudyMsg.setMajorId(Integer.parseInt(major_id));
                    stuStudyMsg.setSemester(semester);
                    if(!StringUtils.isEmpty(term))
                        stuStudyMsg.setTerm(Integer.parseInt(term));
                    if(!StringUtils.isEmpty(study_status))
                        stuStudyMsg.setStudyStatus(Integer.parseInt(study_status));
                    if(!StringUtils.isEmpty(is_in_school))
                        stuStudyMsg.setIsInSchool(Integer.parseInt(is_in_school));
                    stuStudyMsg.setCultivateLevel(cultivate_level);
                    stuStudyMsg.setEducationLevel(education_level);
                    stuStudyMsg.setCategory(category);
                    stuStudyMsg.setUpdated(DateUtils.getNow());
                    //插入前校验专业最近修改时间
                    Date updated_2 = stuMajorService.selectByPrimaryKey(stuMajor.getId()).getUpdated();
                    //如果两个时间相等，则提交事务
                    if(DateUtils.dateToStrLong(updated_recent).equals(DateUtils.dateToStrLong(updated_2))){
                        //插入
                        stuMajor.setNumber(++number);
                        stuMajor.setUpdated(DateUtils.getNow());
                        stuMajorService.updateByPrimaryKeySelective(stuMajor);
                        stuStudyMsgMapper.insertSelective(stuStudyMsg);
                        stuBaseMsgMapper.insertSelective(stuBaseMsg);
                    }else{
                        //重新写入该行信息
                        i--;
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 生成学生学号
     * @param academy_id_str 院系编号
     * @param major_id_str 专业编号
     * @param enrollmentDate 入学日期
     * @param num 专业人数
     * @return 学号
     */
    private String creatStuId(String academy_id_str, String major_id_str, String enrollmentDate, Integer num){
        //如果院系编号小于10，则在前面补0
        String stuId = "";
        //判断空串
        if(StringUtils.isEmpty(academy_id_str))
            return null;
        if(StringUtils.isEmpty(major_id_str))
            return null;
        if(StringUtils.isEmpty(enrollmentDate))
            return null;
        if(num < 0)
            return null;
        //类型转换
        int academyId = Integer.parseInt(academy_id_str);
        int majorId = Integer.parseInt(major_id_str);
        //生成学号
        if(academyId < 10){
            stuId = "0" + academyId + enrollmentDate.substring(2, 4);
            //如果专业编号小于10，则在前面补0
            if(majorId < 10){
                stuId = stuId + "0" + major_id_str;
            }else {
                stuId = stuId + major_id_str;
            }
        }else{
            stuId = academyId + enrollmentDate.substring(2, 4);
            //如果专业编号小于10，则在前面补0
            if(majorId < 10){
                stuId = stuId + "0" + major_id_str;
            }else {
                stuId = stuId + major_id_str;
            }
        }
        if(num == null){
            num = 0;
        }
        if(num > 99){
            //专业人数过多
            return null;
        }else{
            //专业人数+1
            num++;
            if(num < 10){
                //补齐学号信息
                stuId = stuId + "0" + num;
            }else{
                stuId = stuId + num;
            }
        }
        return stuId;
    }

    /**
     * 导出学生数据
     * @param title 表格名
     * @param headers  列名
     * @param dataset 数据集
     * @param pattern 日期格式
     * @param workbook Excel实体类对象
     * @return 0 导出失败  1 导出成功
     */
    @Override
    public int exportStuMsg(String title, String[] headers, Collection dataset, String pattern, HSSFWorkbook workbook) {
        ExcelUtils.exportExcel("exportSheet", headers, dataset, pattern, workbook);
        return 1;
    }

    /**
     * 查询所有的学籍信息
     * @return List
     */
    @Override
    public List<StuStudyMsg> selectStuStudyMsgAll() {
        return stuStudyMsgMapper.selectAll();
    }
}
