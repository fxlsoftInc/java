package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class HouseType implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4908285172501914397L;

	private Long id;

    private String houseTypeName;

    private String houseTypeNo;

    private Short houseType;

    private Double houseTypeArea;

    private String houseTypeAreaUnit;

    private Short houseTypeNumbser;

    private Float houseTypeActualArea;

    private String houseTypeActualAreaUnit;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName == null ? null : houseTypeName.trim();
    }

    public String getHouseTypeNo() {
        return houseTypeNo;
    }

    public void setHouseTypeNo(String houseTypeNo) {
        this.houseTypeNo = houseTypeNo == null ? null : houseTypeNo.trim();
    }

    public Short getHouseType() {
        return houseType;
    }

    public void setHouseType(Short houseType) {
        this.houseType = houseType;
    }

    public Double getHouseTypeArea() {
        return houseTypeArea;
    }

    public void setHouseTypeArea(Double houseTypeArea) {
        this.houseTypeArea = houseTypeArea;
    }

    public String getHouseTypeAreaUnit() {
        return houseTypeAreaUnit;
    }

    public void setHouseTypeAreaUnit(String houseTypeAreaUnit) {
        this.houseTypeAreaUnit = houseTypeAreaUnit == null ? null : houseTypeAreaUnit.trim();
    }

    public Short getHouseTypeNumbser() {
        return houseTypeNumbser;
    }

    public void setHouseTypeNumbser(Short houseTypeNumbser) {
        this.houseTypeNumbser = houseTypeNumbser;
    }

    public Float getHouseTypeActualArea() {
        return houseTypeActualArea;
    }

    public void setHouseTypeActualArea(Float houseTypeActualArea) {
        this.houseTypeActualArea = houseTypeActualArea;
    }

    public String getHouseTypeActualAreaUnit() {
        return houseTypeActualAreaUnit;
    }

    public void setHouseTypeActualAreaUnit(String houseTypeActualAreaUnit) {
        this.houseTypeActualAreaUnit = houseTypeActualAreaUnit == null ? null : houseTypeActualAreaUnit.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}