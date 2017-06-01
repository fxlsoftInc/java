package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SubHouseInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -930082134854208877L;

	private Long id;

    private Double area;

    private Short type;

    private String typeName;

    private Long houseStyleId;

    private Long addressId;

    private Date planDecorationTime;

    private Date createTime;

    private String remark;

    private Short status;
    
    private String structure;
    
    private String measurer;
    
    private String measurerTelephone;
    
    private Address address;
    
    private List<HouseType> houseTypeList;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Long getHouseStyleId() {
        return houseStyleId;
    }

    public void setHouseStyleId(Long houseStyleId) {
        this.houseStyleId = houseStyleId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Date getPlanDecorationTime() {
        return planDecorationTime;
    }

    public void setPlanDecorationTime(Date planDecorationTime) {
        this.planDecorationTime = planDecorationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public List<HouseType> getHouseTypeList() {
		return houseTypeList;
	}

	public void setHouseTypeList(List<HouseType> houseTypeList) {
		this.houseTypeList = houseTypeList;
	}

	public String getMeasurerTelephone() {
		return measurerTelephone;
	}

	public void setMeasurerTelephone(String measurerTelephone) {
		this.measurerTelephone = measurerTelephone;
	}

	public String getMeasurer() {
		return measurer;
	}

	public void setMeasurer(String measurer) {
		this.measurer = measurer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}