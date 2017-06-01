package com.jialian.api.domain.vo;

import java.io.Serializable;

/**
 *@Description: 首页
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月15日 下午6:09:35
 *@Version:1.0
 */
public class HomeVo implements Serializable{
	
	private static final long serialVersionUID = 4830993773892987790L;

	//案例馆id
	private Long id;

	//图片路径
	private String path;
	
	//图片名
	private String name;
	
	//图片格式
	private String extension;

	//设计方案介绍
	private String caseDesignIntroduction;
	
	//房屋类型名称
	private String houseTypeName;
	
	//房屋风格名称
	private String styleName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCaseDesignIntroduction() {
		return caseDesignIntroduction;
	}
	public void setCaseDesignIntroduction(String caseDesignIntroduction) {
		this.caseDesignIntroduction = caseDesignIntroduction;
	}
	public String getHouseTypeName() {
		return houseTypeName;
	}
	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
}
