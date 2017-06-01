package com.jialian.api.domain.para;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.HouseType;

public class SubHouseInfoPara implements Serializable {

	private static final long serialVersionUID = -3239430104370059750L;

	private Long id;
	
	private Double area;
	 
	private Short type;
	 
	private Date planDecorationTime;
	
	private Long houseStyleId;
	
	private String measurer;
    
    private String measurerTelephone;

    private Boolean isActual;
    
    private Address address;
    
    private List<HouseType> houseTypeList;

    private Long userId;
    
    private String orderNo;
    
    private Long orderId;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getPlanDecorationTime() {
		return planDecorationTime;
	}

	public void setPlanDecorationTime(Date planDecorationTime) {
		this.planDecorationTime = planDecorationTime;
	}

	public Long getHouseStyleId() {
		return houseStyleId;
	}

	public void setHouseStyleId(Long houseStyleId) {
		this.houseStyleId = houseStyleId;
	}

	public String getMeasurer() {
		return measurer;
	}

	public void setMeasurer(String measurer) {
		this.measurer = measurer;
	}

	public String getMeasurerTelephone() {
		return measurerTelephone;
	}

	public void setMeasurerTelephone(String measurerTelephone) {
		this.measurerTelephone = measurerTelephone;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<HouseType> getHouseTypeList() {
		return houseTypeList;
	}

	public void setHouseTypeList(List<HouseType> houseTypeList) {
		this.houseTypeList = houseTypeList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
