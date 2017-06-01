package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SignedRecord implements Serializable{
	
	private static final long serialVersionUID = 1097658268238592406L;

	private Long id;

    private String orderNo;

    private String contractName;

    private String contractNo;

    private Short contractStatus;

    private Double contractAmt;

    private Date signedTime;

    private Date contractStartTime;

    private Date contractEndTime;

    private Double contractFirstAmt;

    private Double contractLastAmt;

    private String contractFirstCondition;

    private String contractLastCondition;

    private String contractContent;

    private Date createTime;

    private Date updateTime;

    private Short status;

    private String remark;

    private List<ResourceInfo> resourceInfo;
    
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
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public Short getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Short contractStatus) {
        this.contractStatus = contractStatus;
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
        this.contractFirstCondition = contractFirstCondition == null ? null : contractFirstCondition.trim();
    }

    public String getContractLastCondition() {
        return contractLastCondition;
    }

    public void setContractLastCondition(String contractLastCondition) {
        this.contractLastCondition = contractLastCondition == null ? null : contractLastCondition.trim();
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent == null ? null : contractContent.trim();
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

	public List<ResourceInfo> getResourceInfo() {
		return resourceInfo;
	}

	public void setResourceInfo(List<ResourceInfo> resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
}