/**
 * 
 */
package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Param:
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2016年1月18日上午11:42:05
 * @Version:1.0
 */
public class DecorationOrderVO implements Serializable{

	private static final long serialVersionUID = 2982058294968092396L;

	private Long id;
	
	private String orderNo;
	
	private String userName;
	
	private String telphone;
	
	private String comboName;
	
	private Date orderTime;
	
	private Short status;
	
	private String employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	
}
