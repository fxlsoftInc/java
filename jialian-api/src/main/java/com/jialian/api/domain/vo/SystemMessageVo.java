package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class SystemMessageVo implements Serializable{

	/** serialVersionUID:（用一句话描述这个变量表示什么） */
	
	private static final long serialVersionUID = -4982546406144035283L;
     
	private Integer id;

    private Integer userId;//接收者id

    private String messageTitle;

    private String messageContent;

    private Integer messageStatus;

    private Date sendTime;

    private String remark;

    private Integer status;
    
    private Integer sendType;//发送方式(1、短信  2、站内信)
    
    private Integer sendObj;//发送对象(1、批量发送 2、发送给个人)
    
    private String sendObjId;//批量发送对象id，用逗号隔开
    
    private String telephone;//单个发送者电话
    
    private Integer send_time_type;//发送时间类型(1、即刻发送，2、预约时间发送)
    
    private Integer send_user_id;//发送者id
    
    
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

	public Integer getSend_time_type() {
		return send_time_type;
	}

	public void setSend_time_type(Integer send_time_type) {
		this.send_time_type = send_time_type;
	}

	public Integer getSend_user_id() {
		return send_user_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setSend_user_id(Integer send_user_id) {
		this.send_user_id = send_user_id;
	}

	public String getSendObjId() {
		return sendObjId;
	}

	public void setSendObjId(String sendObjId) {
		this.sendObjId = sendObjId;
	}
}
