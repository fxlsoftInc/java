package com.jialian.api.domain.entity;

import java.util.Date;

public class HouseSurvey {
    private Long id;

    private Long subBouseInfoId;

    private Long houseStructureCategoryId;

    private String surveyName;

    private Double surveyValue;

    private String surveyUnit;

    private Date createTime;

    private Date updateTime;

    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubBouseInfoId() {
        return subBouseInfoId;
    }

    public void setSubBouseInfoId(Long subBouseInfoId) {
        this.subBouseInfoId = subBouseInfoId;
    }

    public Long getHouseStructureCategoryId() {
        return houseStructureCategoryId;
    }

    public void setHouseStructureCategoryId(Long houseStructureCategoryId) {
        this.houseStructureCategoryId = houseStructureCategoryId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName == null ? null : surveyName.trim();
    }

    public Double getSurveyValue() {
        return surveyValue;
    }

    public void setSurveyValue(Double surveyValue) {
        this.surveyValue = surveyValue;
    }

    public String getSurveyUnit() {
        return surveyUnit;
    }

    public void setSurveyUnit(String surveyUnit) {
        this.surveyUnit = surveyUnit == null ? null : surveyUnit.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}