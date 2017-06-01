package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class HouseStyle implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4315109380895855219L;

	private Long id;

    private String styleName;

    private Short styleType;

    private String styleTypeName;

    private Short styleStatus;

    private Date createTime;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName == null ? null : styleName.trim();
    }

    public Short getStyleType() {
        return styleType;
    }

    public void setStyleType(Short styleType) {
        this.styleType = styleType;
    }

    public String getStyleTypeName() {
        return styleTypeName;
    }

    public void setStyleTypeName(String styleTypeName) {
        this.styleTypeName = styleTypeName == null ? null : styleTypeName.trim();
    }

    public Short getStyleStatus() {
        return styleStatus;
    }

    public void setStyleStatus(Short styleStatus) {
        this.styleStatus = styleStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}