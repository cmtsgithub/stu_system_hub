package com.stu.pojo;

import java.io.Serializable;
import java.util.Date;

public class StuStudyMsg implements Serializable {
    private Integer id;

    private String stuId;

    private String semester;

    private Integer term;

    private Integer academyId;

    private Integer majorId;

    private Integer studyStatus;

    private Integer isInSchool;

    private String educationLevel;

    private String cultivateLevel;

    private String category;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }

    public Integer getIsInSchool() {
        return isInSchool;
    }

    public void setIsInSchool(Integer isInSchool) {
        this.isInSchool = isInSchool;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel == null ? null : educationLevel.trim();
    }

    public String getCultivateLevel() {
        return cultivateLevel;
    }

    public void setCultivateLevel(String cultivateLevel) {
        this.cultivateLevel = cultivateLevel == null ? null : cultivateLevel.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }


    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}