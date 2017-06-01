package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class SubLookVo implements Serializable{

	private static final long serialVersionUID = 3964641817451216355L;

	private Long id;
	
	private String telphone;
	
	private String remark;
	
	private Date subTime;
	
	private Short status;
	
	private Short orderStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}
}
