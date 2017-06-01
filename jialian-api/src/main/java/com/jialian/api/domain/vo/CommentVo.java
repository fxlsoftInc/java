package com.jialian.api.domain.vo;

import java.io.Serializable;

public class CommentVo implements Serializable{

	/** serialVersionUID:（用一句话描述这个变量表示什么） */
	
	private static final long serialVersionUID = 6481125323684987491L;

	private Long id;

    private String userName;

    private String content;

	private String path;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
