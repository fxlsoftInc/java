package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Subscribe implements Serializable{
	
	private static final long serialVersionUID = 3409796237105522946L;

	private Long id;

    private Long userid;

    private String telphone;

    private Short type;

    private String remark;

    private Date subTime;

    private Date createTime;

    private Long subFromId;

    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSubFromId() {
        return subFromId;
    }

    public void setSubFromId(Long subFromId) {
        this.subFromId = subFromId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}