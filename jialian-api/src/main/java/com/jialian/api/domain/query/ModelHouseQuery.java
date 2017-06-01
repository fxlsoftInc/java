package com.jialian.api.domain.query;

import java.io.Serializable;

/**
 *@Description:样板房信息查询封装类
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月11日 下午4:59:16
 *@Version:1.0
 */
public class ModelHouseQuery implements Serializable{

	private static final long serialVersionUID = -6482678168807768802L;

	private String province;

	private String city;
	
	private String county;

	//分页条件
	private int currentPage=0;
	 
	private int startIndex=0;
	 
	private int onePageCount=10;
	 
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
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
