package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 * 角色查询封装类
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2016年1月4日 下午3:01:28
 *@Version:1.0
 */
public class AdminRoleQuery implements Serializable{

	/** serialVersionUID:序列号 */
	
	private static final long serialVersionUID = -5487647393185915906L;
	
	//分页条件
	private int currentPage=0;
		 
	private int startIndex=0;
		 
	private int onePageCount=10;
	
    private String roleName;//角色名称
    
	//排序
	private Short sort=0;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
