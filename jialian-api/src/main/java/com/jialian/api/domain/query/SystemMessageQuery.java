package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 * 系统消息信息查询封装类
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月27日 下午8:19:41
 *@Version:1.0
 */
public class SystemMessageQuery implements Serializable{
	
	private static final long serialVersionUID = 146465309513768194L;

	private int userId;       //用户id
	//分页条件
  	private int currentPage=0;  
  	 
  	private int startIndex=0;
  	 
  	private int onePageCount=10;
  	
    //排序
  	private Short sort=0;

  	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
