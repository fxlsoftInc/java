package com.jialian.api.domain.query;

import java.io.Serializable;

public class OrderQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1165676428765737913L;

	private Long id;//订单id
	
	private String telphone;//用户电话
	
	private Short orderStatus;//状态
	
	private String subTime;//预约时间
	
	private Short sort=0;//排序(0.倒序，1.升序)
	    
	private int currentPage=0;//查询页(前端)
	 
	private int startIndex=0;//起始页(数据库)
	
	private int onePageCount=10;//查询条数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Short getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSubTime() {
		return subTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
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
