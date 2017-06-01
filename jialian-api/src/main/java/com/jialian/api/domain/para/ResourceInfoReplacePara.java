package com.jialian.api.domain.para;

import java.io.Serializable;

public class ResourceInfoReplacePara implements Serializable{

	private static final long serialVersionUID = -9175318453455887739L;

	private Long fromId;//被替换的图片
	
	private Long targetId;//目标图片
	
	private String fromNo;//
	
	private String targetNo;

	private String remark;
	
	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getFromNo() {
		return fromNo;
	}

	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}

	public String getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
