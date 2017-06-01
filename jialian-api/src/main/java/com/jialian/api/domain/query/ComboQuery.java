package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 *@Description: 套餐查询封装类
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2016年1月4日 下午3:43:28
 *@Version:1.0
 */
public class ComboQuery implements Serializable{

	private static final long serialVersionUID = 1185803474695897563L;

	private Long id;//套餐id
	
	private String name;//套餐名称
	
	private int currentPage=0;//查询页
	 
	private int startIndex=0;//(数据库查询)起始页
	 
	private int onePageCount=10;//(显示条数)
	
	private Short sort=0;//排序(0.降序，1.升序)
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
