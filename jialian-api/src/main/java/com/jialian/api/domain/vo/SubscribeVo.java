package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *@Description: 套餐预约
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月16日 下午5:05:27
 *@Version:1.0
 */
public class SubscribeVo implements Serializable{
	
	private static final long serialVersionUID = -8444749394373100223L;

	private Long id;//用户id

	private Long userId;
	
	private Long comboId;
	
	private String telphone;//手机号
	
	private Double area;//房屋面积
	
	private Short type;

	private String typeName;
	
	private Long houseStyleId;
	
	private Date planDecorationTime;
	 
	private String structure;
	
	private String province;

    private String city;

    private String county;
    
    private String address;
    
    private Short s;
    
    private Short t;
    
    private Short c;
    
    private Short w;
    
    private Short y;
    
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
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getPlanDecorationTime() {
		return planDecorationTime;
	}
	public void setPlanDecorationTime(Date planDecorationTime) {
		this.planDecorationTime = planDecorationTime;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getHouseStyleId() {
		return houseStyleId;
	}
	public void setHouseStyleId(Long houseStyleId) {
		this.houseStyleId = houseStyleId;
	}
	public Short getS() {
		return s;
	}
	public void setS(Short s) {
		this.s = s;
	}
	public Short getT() {
		return t;
	}
	public void setT(Short t) {
		this.t = t;
	}
	public Short getC() {
		return c;
	}
	public void setC(Short c) {
		this.c = c;
	}
	public Short getW() {
		return w;
	}
	public void setW(Short w) {
		this.w = w;
	}
	public Short getY() {
		return y;
	}
	public void setY(Short y) {
		this.y = y;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getComboId() {
		return comboId;
	}
	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}
}
