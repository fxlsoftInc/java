package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 *@Description: 用户信息查询封装
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月18日 上午11:55:05
 *@Version:1.0
 */
public class UserQuery implements Serializable{
	
	private static final long serialVersionUID = -6733370216584289035L;
	
    private String nickName;//用户名称

    private String telephone;//手机号

	//分页条件
	private int currentPage=0;
	 
	private int startIndex=0;
	 
	private int onePageCount=10;
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
