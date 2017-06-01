package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SemDecorationOrderVO implements Serializable{
	
	private static final long serialVersionUID = 6291301849820214987L;
	
	private Long id;

    private String orderNo;

    private Long userId;

    private Double orderTotalPrice;

    private Double orderSemPrice;
    
    private Date createTime;

    private Date updateTime;

    private Short orderType;

    private String remark;
    
	private List<SemDecOrderInfoVO> semDecOrderInfo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Short getOrderType() {
		return orderType;
	}
	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}
	public List<SemDecOrderInfoVO> getSemDecOrderInfo() {
		return semDecOrderInfo;
	}
	public void setSemDecOrderInfo(List<SemDecOrderInfoVO> semDecOrderInfo) {
		this.semDecOrderInfo = semDecOrderInfo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
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
		this.remark = remark;
	}
	public Double getOrderSemPrice() {
		return orderSemPrice;
	}
	public void setOrderSemPrice(Double orderSemPrice) {
		this.orderSemPrice = orderSemPrice;
	}
	
}
