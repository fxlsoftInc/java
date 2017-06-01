package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 *@Description: 后台用户查询封装类
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2016年1月13日 下午2:27:02
 *@Version:1.0
 */
public class AdminUserQuery implements Serializable{

	private static final long serialVersionUID = 2157061391154844085L;

	private String username;//账户名

    private Short sort=0;//排序(0.倒序，1.升序)
    
	private int currentPage=0;//查询页(前端)
	 
	private int startIndex=0;//起始页(数据库)
	 
	private int onePageCount=10;//查询条数
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Short getSort() {
		return sort;
	}
	public void setSort(Short sort) {
		this.sort = sort;
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
}
