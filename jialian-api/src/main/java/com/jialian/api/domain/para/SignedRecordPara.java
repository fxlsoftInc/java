package com.jialian.api.domain.para;

import java.io.Serializable;
import java.util.Date;

public class SignedRecordPara implements Serializable {

	private static final long serialVersionUID = -4078871644038267730L;
	
	private Long userId;
	
	private Long orderId;
	
	private String orderNo;
	
	private Long id;
	
	private Double contractAmt;
	
	private Date signedTime;
	
	private Date contractStartTime;

    private Date contractEndTime;

    private Double contractFirstAmt;

    private Double contractLastAmt;

    private String contractFirstCondition;

    private String contractLastCondition;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(Double contractAmt) {
		this.contractAmt = contractAmt;
	}

	public Date getSignedTime() {
		return signedTime;
	}

	public void setSignedTime(Date signedTime) {
		this.signedTime = signedTime;
	}

	public Date getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public Double getContractFirstAmt() {
		return contractFirstAmt;
	}

	public void setContractFirstAmt(Double contractFirstAmt) {
		this.contractFirstAmt = contractFirstAmt;
	}

	public Double getContractLastAmt() {
		return contractLastAmt;
	}

	public void setContractLastAmt(Double contractLastAmt) {
		this.contractLastAmt = contractLastAmt;
	}

	public String getContractFirstCondition() {
		return contractFirstCondition;
	}

	public void setContractFirstCondition(String contractFirstCondition) {
		this.contractFirstCondition = contractFirstCondition;
	}

	public String getContractLastCondition() {
		return contractLastCondition;
	}

	public void setContractLastCondition(String contractLastCondition) {
		this.contractLastCondition = contractLastCondition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
}
