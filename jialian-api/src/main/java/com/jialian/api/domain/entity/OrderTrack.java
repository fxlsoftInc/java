package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderTrack implements Serializable{

	private static final long serialVersionUID = -7944264431055122066L;

	private Long id;

    private Long userId;

    private Long orderId;

    private String trackTitle;

    private String trackContent;

    private Date trackTime;

    private Short trackType;

    private Long trackId;

    private Short trackTatus;

    private String orderDesc;

    private Date updateTime;

    private Date creatTime;

    private Long status;

    private String remark;

    private Short trackOrderType;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle == null ? null : trackTitle.trim();
    }

    public String getTrackContent() {
        return trackContent;
    }

    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent == null ? null : trackContent.trim();
    }

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public Short getTrackType() {
        return trackType;
    }

    public void setTrackType(Short trackType) {
        this.trackType = trackType;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Short getTrackTatus() {
        return trackTatus;
    }

    public void setTrackTatus(Short trackTatus) {
        this.trackTatus = trackTatus;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public Short getTrackOrderType() {
		return trackOrderType;
	}

	public void setTrackOrderType(Short trackOrderType) {
		this.trackOrderType = trackOrderType;
	}
}