package com.jialian.api.domain.vo;

import java.io.Serializable;

public class CollectVo implements Serializable{

	private static final long serialVersionUID = 8207136401738811553L;

	private long parentId;//被收藏id
	
	private String caseHouse;//案例馆名称
	
	private String styleName;//风格名称
	
	private String path;//图片路径
	
	private long collectCount;//收藏数


	public long getParentId() {
		return parentId;
	}


	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	public String getCaseHouse() {
		return caseHouse;
	}


	public void setCaseHouse(String caseHouse) {
		this.caseHouse = caseHouse;
	}


	public String getStyleName() {
		return styleName;
	}


	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public long getCollectCount() {
		return collectCount;
	}


	public void setCollectCount(long collectCount) {
		this.collectCount = collectCount;
	}
	
	
}
