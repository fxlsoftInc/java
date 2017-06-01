package com.jialian.api.domain.vo;

import java.io.Serializable;

/**
 * @Description: 样板房返回信息类
 * @Author: 卢光磊 lgl1012cto@foxmail.com
 * @Since:2015年12月21日 下午3:07:42
 * @Version:1.0
 */
public class ModelHouseVo implements Serializable {

	private static final long serialVersionUID = 7583058210194298078L;

	// 样板房id
	private Long id;

	private String houseName;

	private Short houseNumber;

	private String province;

	private String city;

	private String county;

	private String address;

	private String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Short getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Short houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

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
}
