package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SubOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1679158930837676790L;

	private Long id;

    private Date createTime;

    private Date endTime;

    private Short orderStatus;

    private Double orderMoney;

    private String currency;

    private Long userId;

    private Long subscribeId;

    private Long subHouseInfoId;

    private Long actualSubHouseInfoId;
    
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
        this.currency = currency == null ? null : currency.trim();
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

    public Long getSubHouseInfoId() {
        return subHouseInfoId;
    }

    public void setSubHouseInfoId(Long subHouseInfoId) {
        this.subHouseInfoId = subHouseInfoId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	public Long getActualSubHouseInfoId() {
		return actualSubHouseInfoId;
	}

	public void setActualSubHouseInfoId(Long actualSubHouseInfoId) {
		this.actualSubHouseInfoId = actualSubHouseInfoId;
	}
}