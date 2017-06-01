package com.jialian.api.domain.query;

import java.io.Serializable;

public class RoleQuery implements Serializable{

	private static final long serialVersionUID = 5954330587217380312L;
	
	private String roleName;//角色名称

	private int currentPage=0;//查询页
	 
	private int startIndex=0;//(数据库查询)起始页
	 
	private int onePageCount=10;//(显示条数)
	
	private Short sort=0;//排序(0.降序，1.升序)
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public Short getSort() {
		return sort;
	}
	public void setSort(Short sort) {
		this.sort = sort;
	}
}
