package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Memorando implements Serializable{

	private static final long serialVersionUID = -7446016758961156260L;

	private Long id;

    private Long userId;

    private String memoNo;

    private String orderNo;
    
    private Short memoType;

    private Long memoId;

    private String memoTitle;

    private String memoContent;

    private Date createTime;

    private Date updateTime;

    private Short status;

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

    public String getMemoNo() {
        return memoNo;
    }

    public void setMemoNo(String memoNo) {
        this.memoNo = memoNo == null ? null : memoNo.trim();
    }

    public Short getMemoType() {
        return memoType;
    }

    public void setMemoType(Short memoType) {
        this.memoType = memoType;
    }

    public Long getMemoId() {
        return memoId;
    }

    public void setMemoId(Long memoId) {
        this.memoId = memoId;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle == null ? null : memoTitle.trim();
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent == null ? null : memoContent.trim();
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}