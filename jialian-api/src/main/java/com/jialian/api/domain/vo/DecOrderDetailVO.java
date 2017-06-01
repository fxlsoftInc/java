package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.jialian.api.domain.entity.User;

public class DecOrderDetailVO implements Serializable{

	private static final long serialVersionUID = 8234053284390990633L;

	private Long id;

    private String orderNo;

    private Long userId;

    private Long employeeId;

    private Long storeId;

    private Long semiDecorationOrderId;

    private Long materialOrderId;

    private Long comboId;
    
    private Double totalAmt;

    private Double discountAmt;

    private Double orderAmt;

    private Double prepayAmt;

    private Double payedAmt;

    private Short orderStatus;

    private Short status;

    private Date confirmTime;

    private Date completeTime;

    private Date createTime;

    private Date updateTime;

    private String remark;
    
    private User user;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getSemiDecorationOrderId() {
		return semiDecorationOrderId;
	}

	public void setSemiDecorationOrderId(Long semiDecorationOrderId) {
		this.semiDecorationOrderId = semiDecorationOrderId;
	}

	public Long getMaterialOrderId() {
		return materialOrderId;
	}

	public void setMaterialOrderId(Long materialOrderId) {
		this.materialOrderId = materialOrderId;
	}

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Double getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(Double orderAmt) {
		this.orderAmt = orderAmt;
	}

	public Double getPrepayAmt() {
		return prepayAmt;
	}

	public void setPrepayAmt(Double prepayAmt) {
		this.prepayAmt = prepayAmt;
	}

	public Double getPayedAmt() {
		return payedAmt;
	}

	public void setPayedAmt(Double payedAmt) {
		this.payedAmt = payedAmt;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
