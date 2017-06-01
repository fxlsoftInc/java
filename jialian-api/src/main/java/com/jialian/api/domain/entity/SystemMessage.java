package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemMessage implements Serializable{
	
	private static final long serialVersionUID = 2176226383978359388L;

	private Integer id;

    private Integer userId;

    private String messageTitle;

    private String messageContent;

    private Integer messageStatus;

    private Date sendTime;

    private Date updateTime;

    private String remark;

    private Date creatTime;

    private Integer status;
    
    private Integer sendType;//发送方式(1、短信  2、站内信)
    
    private Integer sendObj;//发送对象(1、批量发送 2、发送给个人)
    
    private Integer sendTimeType;//发送时间类型(1、即刻发送，2、预约时间发送)
    
    private Integer sendUserId;//发送者id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Integer getSendObj() {
		return sendObj;
	}

	public void setSendObj(Integer sendObj) {
		this.sendObj = sendObj;
	}

	public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getSendTimeType() {
		return sendTimeType;
	}

	public void setSendTimeType(Integer sendTimeType) {
		this.sendTimeType = sendTimeType;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
}