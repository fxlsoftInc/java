package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 *@Description:案例馆信息查询封装类
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月12日 下午1:41:04
 *@Version:1.0
 */
public class CaseHouseQuery implements Serializable{

	private static final long serialVersionUID = 1994127593315083317L;

	private Short houseType;
	
	 private String buildingTypeName;
	 
	 private String styleName;
	 
	 private String houseColor;
	 
	 private Short caseStatus;
	 
	 private Short status;
	 
	 private Short sort=0;
	 
	 //分页条件
	 private int currentPage=0;
	 
	 private int startIndex=0;
	 
	 private int onePageCount=15;

	public Short getHouseType() {
		return houseType;
	}

	public void setHouseType(Short houseType) {
		this.houseType = houseType;
	}

	public String getBuildingTypeName() {
		return buildingTypeName;
	}

	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getHouseColor() {
		return houseColor;
	}

	public void setHouseColor(String houseColor) {
		this.houseColor = houseColor;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Short getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(Short caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
