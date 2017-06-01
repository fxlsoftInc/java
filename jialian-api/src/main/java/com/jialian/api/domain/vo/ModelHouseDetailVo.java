package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.ResourceInfo;

/**
 * 后台--样板房详情封装类
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2016年1月13日 下午7:29:51
 *@Version:1.0
 */
public class ModelHouseDetailVo implements Serializable{

	/** serialVersionUID:（用一句话描述这个变量表示什么） */
	
	private static final long serialVersionUID = -1935308180096091751L;
     
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
    
    private Address addressDetail;
    
    private ResourceInfo resourceInfo;

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
		this.houseName = houseName;
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
		this.telphone = telphone;
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
		this.remark = remark;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public Address getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(Address addressDetail) {
		this.addressDetail = addressDetail;
	}

	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}

	public void setResourceInfo(ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
    
    
}
