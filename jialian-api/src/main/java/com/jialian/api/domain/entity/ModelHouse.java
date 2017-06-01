package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class ModelHouse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3053329285961160550L;

	private Long id;

    private String houseName;

    private Long address;

    private Short houseNumber;

    private String telphone;

    private Short houseStatus;

    private Date creatTime;

    private Date updateTime;

    private Short status;

    private String remark;

    private String modelNum;//房屋编号
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Short getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public Short getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Short houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
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

    public String getRemark() {
        return remark;
    }

	public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
	
	 public String getModelNum() {
			return modelNum;
	 }

		public void setModelNum(String modelNum) {
			this.modelNum = modelNum;
		}
}