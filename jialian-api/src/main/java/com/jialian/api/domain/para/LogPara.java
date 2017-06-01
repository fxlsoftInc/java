package com.jialian.api.domain.para;

import java.io.Serializable;
import java.util.Date;

public class LogPara implements Serializable{

	private static final long serialVersionUID = 8838464101580288641L;

	private Date startTime;
	
	private Date endTime;
	
	private String userName;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
