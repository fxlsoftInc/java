package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class HouseInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Short houseType;

    private String houseTypeName;
    
    private String houseColor;

    private Long addressId;

    private Short buildingType;

    private String buildingTypeName;

    private Double houseTotalArea;

    private String houseTotalAreaUnit;

    private String houseTypeNo;

    private Date createTime;

    private Date updateTime;

    private Short houseStatus;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getHouseType() {
        return houseType;
    }

    public void setHouseType(Short houseType) {
        this.houseType = houseType;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName == null ? null : houseTypeName.trim();
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Short getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Short buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingTypeName() {
        return buildingTypeName;
    }

    public void setBuildingTypeName(String buildingTypeName) {
        this.buildingTypeName = buildingTypeName == null ? null : buildingTypeName.trim();
    }
    
    public String getHouseColor() {
		return houseColor;
	}

	public void setHouseColor(String houseColor) {
		this.houseColor = houseColor;
	}

	public Double getHouseTotalArea() {
        return houseTotalArea;
    }

    public void setHouseTotalArea(Double houseTotalArea) {
        this.houseTotalArea = houseTotalArea;
    }

    public String getHouseTotalAreaUnit() {
        return houseTotalAreaUnit;
    }

    public void setHouseTotalAreaUnit(String houseTotalAreaUnit) {
        this.houseTotalAreaUnit = houseTotalAreaUnit == null ? null : houseTotalAreaUnit.trim();
    }

    public String getHouseTypeNo() {
        return houseTypeNo;
    }

    public void setHouseTypeNo(String houseTypeNo) {
        this.houseTypeNo = houseTypeNo == null ? null : houseTypeNo.trim();
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

    public Short getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Short houseStatus) {
        this.houseStatus = houseStatus;
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
}