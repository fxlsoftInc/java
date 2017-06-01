package com.jialian.api.domain.query;

import java.io.Serializable;

import com.jialian.api.domain.entity.ResourceInfo;

public class CommentQuery implements Serializable{

	/** serialVersionUID:（用一句话描述这个变量表示什么） */
	
	private static final long serialVersionUID = -7527979544209902005L;
	
	private Long id;

    private String userName;

    private String content;

    private Integer status;
    
    private ResourceInfo resourceInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}

	public void setResourceInfo(ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
}
