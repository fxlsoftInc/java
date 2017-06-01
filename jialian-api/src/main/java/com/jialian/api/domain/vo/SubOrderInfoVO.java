package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.jialian.api.domain.entity.SubHouseInfo;

public class SubOrderInfoVO implements Serializable{
	
	private static final long serialVersionUID = 1979003260521670630L;

	private Long id;

    private Date createTime;

    private Date endTime;

    private Short orderStatus;

    private Double orderMoney;

    private String currency;

    private Long userId;

    private Long subscribeId;

    private SubHouseInfo subHouseInfo;

    private SubHouseInfo acutalSubHouseInfo;
    
    private String orderNo;

    private Short status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}

	public SubHouseInfo getSubHouseInfo() {
		return subHouseInfo;
	}

	public void setSubHouseInfo(SubHouseInfo subHouseInfo) {
		this.subHouseInfo = subHouseInfo;
	}

	public SubHouseInfo getAcutalSubHouseInfo() {
		return acutalSubHouseInfo;
	}

	public void setAcutalSubHouseInfo(SubHouseInfo acutalSubHouseInfo) {
		this.acutalSubHouseInfo = acutalSubHouseInfo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
    
}
