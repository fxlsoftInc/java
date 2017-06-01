package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class CaseHouseInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3952003689527019593L;

	private Long id;

    private Long parentId;

    private String detailName;

    private Short detailType;

    private Long detailId;

    private Double detailPrice;

    private Integer detailNumber;

    private Short detailStatus;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName == null ? null : detailName.trim();
    }

    public Short getDetailType() {
        return detailType;
    }

    public void setDetailType(Short detailType) {
        this.detailType = detailType;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Double getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(Double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Integer getDetailNumber() {
        return detailNumber;
    }

    public void setDetailNumber(Integer detailNumber) {
        this.detailNumber = detailNumber;
    }

    public Short getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Short detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
}