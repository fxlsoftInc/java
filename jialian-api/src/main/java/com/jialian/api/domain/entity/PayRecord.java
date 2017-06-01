package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class PayRecord implements Serializable{

	private static final long serialVersionUID = -4955259665390160702L;

	private Long id;

    private String payNo;

    private Long orderId;

    private String orderNo;
    
    private Short orderType;

    private Long userId;

    private String userAccountNumber;

    private Long accessUserId;

    private String accessUserAccountNumber;

    private Double payableAmt;

    private Double actualAmt;

    private Double discountAmt;

    private Short payType;

    private Short payStatus;

    private String payDescription;

    private Date payTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Short recordType;
    
    private String objid;
    
    private User user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccountNumber() {
        return userAccountNumber;
    }

    public void setUserAccountNumber(String userAccountNumber) {
        this.userAccountNumber = userAccountNumber == null ? null : userAccountNumber.trim();
    }

    public Long getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(Long accessUserId) {
        this.accessUserId = accessUserId;
    }

    public String getAccessUserAccountNumber() {
        return accessUserAccountNumber;
    }

    public void setAccessUserAccountNumber(String accessUserAccountNumber) {
        this.accessUserAccountNumber = accessUserAccountNumber == null ? null : accessUserAccountNumber.trim();
    }

    public Double getPayableAmt() {
        return payableAmt;
    }

    public void setPayableAmt(Double payableAmt) {
        this.payableAmt = payableAmt;
    }

    public Double getActualAmt() {
        return actualAmt;
    }

    public void setActualAmt(Double actualAmt) {
        this.actualAmt = actualAmt;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Short getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription == null ? null : payDescription.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

	/**
	 * @return recordType
	 */
	public Short getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType 要设置的 recordType
	 */
	public void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return objid
	 */
	public String getObjid() {
		return objid;
	}

	/**
	 * @param objid 要设置的 objid
	 */
	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}