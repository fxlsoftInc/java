package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.ResourceInfo;

public class ComboComplexVO implements Serializable{
	
	private static final long serialVersionUID = -5407798820652048937L;

	private Long id;

    private String name;

    private String no;
    
    private Short type;

    private Double price;

    private String currency;

    private String unit;

    private String comboRemark;

    private Date createTime;

    private Short comboStatus;

    private Date updateTime;

    private String remark;

    private Short status;

    private String introduction;

    private String service;
    
    private List<ResourceInfo> resourceInfoList;
    
    private List<ComboInfo> comboInfoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getComboRemark() {
		return comboRemark;
	}

	public void setComboRemark(String comboRemark) {
		this.comboRemark = comboRemark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(Short comboStatus) {
		this.comboStatus = comboStatus;
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
		this.remark = remark;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public List<ResourceInfo> getResourceInfoList() {
		return resourceInfoList;
	}

	public void setResourceInfoList(List<ResourceInfo> resourceInfoList) {
		this.resourceInfoList = resourceInfoList;
	}

	public List<ComboInfo> getComboInfoList() {
		return comboInfoList;
	}

	public void setComboInfoList(List<ComboInfo> comboInfoList) {
		this.comboInfoList = comboInfoList;
	}
}
