package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class CaseHouse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6549049209113508697L;

	private Long id;

    private String caseHose;

    private Long caseStyleId;
    
    private Double casePrice;

    private Integer casePopularity;

    private Integer caseSubscribeNumber;

    private Long addressId;

    private Float caseArea;

    private String caseAreaUnit;

    private Long houseInfoId;

    private String decorate;

    private String caseDesignStyle;

    private String manager;

    private Date createTime;

    private Date updateTime;

    private Short caseStatus;

    private Short status;

    private String remark;

    private String caseDesignIntroduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseHose() {
        return caseHose;
    }

    public void setCaseHose(String caseHose) {
        this.caseHose = caseHose == null ? null : caseHose.trim();
    }

    public Long getCaseStyleId() {
        return caseStyleId;
    }

    public void setCaseStyleId(Long caseStyleId) {
        this.caseStyleId = caseStyleId;
    }

    public Double getCasePrice() {
        return casePrice;
    }

    public void setCasePrice(Double casePrice) {
        this.casePrice = casePrice;
    }

    public Integer getCasePopularity() {
        return casePopularity;
    }

    public void setCasePopularity(Integer casePopularity) {
        this.casePopularity = casePopularity;
    }

    public Integer getCaseSubscribeNumber() {
        return caseSubscribeNumber;
    }

    public void setCaseSubscribeNumber(Integer caseSubscribeNumber) {
        this.caseSubscribeNumber = caseSubscribeNumber;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Float getCaseArea() {
        return caseArea;
    }

    public void setCaseArea(Float caseArea) {
        this.caseArea = caseArea;
    }

    public String getCaseAreaUnit() {
        return caseAreaUnit;
    }

    public void setCaseAreaUnit(String caseAreaUnit) {
        this.caseAreaUnit = caseAreaUnit == null ? null : caseAreaUnit.trim();
    }

    public Long getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(Long houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate == null ? null : decorate.trim();
    }

    public String getCaseDesignStyle() {
        return caseDesignStyle;
    }

    public void setCaseDesignStyle(String caseDesignStyle) {
        this.caseDesignStyle = caseDesignStyle == null ? null : caseDesignStyle.trim();
    }

	public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
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

    public Short getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Short caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCaseDesignIntroduction() {
        return caseDesignIntroduction;
    }

    public void setCaseDesignIntroduction(String caseDesignIntroduction) {
        this.caseDesignIntroduction = caseDesignIntroduction == null ? null : caseDesignIntroduction.trim();
    }
}