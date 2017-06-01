package com.jialian.api.domain.entity;

import java.util.Date;

public class Collect {
    private Long id;

    private Long userId;

    private Long parentId;

    private Short collectType;

    private Long collectStatus;

    private Date collectTime;

    private Date creatTime;

    private Date updateTime;

    private Long status;

    private String remark;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Short getCollectType() {
        return collectType;
    }

    public void setCollectType(Short collectType) {
        this.collectType = collectType;
    }

    public Long getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Long collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}