package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class MaterialAttribute implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5279616750292421581L;

	private Long id;

    private String attributeNo;

    private String attributeName;

    private String attributeValue;

    private String attributeLong;

    private String attributeWide;

    private String attributeHight;

    private String attributeUnit;

    private Short attributeType;

    private Long materialId;

    private Date createTime;

    private Date updateTime;

    private String remark;
    
    private Integer initialInventory;
    
    private Integer currentInventory;
    
    private Integer sfkf;
    
    private Long priceId;
    
    private MaterialPrice price;

    private Material material;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeNo() {
        return attributeNo;
    }

    public void setAttributeNo(String attributeNo) {
        this.attributeNo = attributeNo == null ? null : attributeNo.trim();
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName == null ? null : attributeName.trim();
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue == null ? null : attributeValue.trim();
    }

    public String getAttributeLong() {
        return attributeLong;
    }

    public void setAttributeLong(String attributeLong) {
        this.attributeLong = attributeLong == null ? null : attributeLong.trim();
    }

    public String getAttributeWide() {
        return attributeWide;
    }

    public void setAttributeWide(String attributeWide) {
        this.attributeWide = attributeWide == null ? null : attributeWide.trim();
    }

    public String getAttributeHight() {
        return attributeHight;
    }

    public void setAttributeHight(String attributeHight) {
        this.attributeHight = attributeHight == null ? null : attributeHight.trim();
    }

    public String getAttributeUnit() {
        return attributeUnit;
    }

    public void setAttributeUnit(String attributeUnit) {
        this.attributeUnit = attributeUnit == null ? null : attributeUnit.trim();
    }

    public Short getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(Short attributeType) {
        this.attributeType = attributeType;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/**
	 * @return initialInventory
	 */
	public Integer getInitialInventory() {
		return initialInventory;
	}

	/**
	 * @param initialInventory 要设置的 initialInventory
	 */
	public void setInitialInventory(Integer initialInventory) {
		this.initialInventory = initialInventory;
	}

	/**
	 * @return currentInventory
	 */
	public Integer getCurrentInventory() {
		return currentInventory;
	}

	/**
	 * @param currentInventory 要设置的 currentInventory
	 */
	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}

	/**
	 * @return priceId
	 */
	public Long getPriceId() {
		return priceId;
	}

	/**
	 * @param priceId 要设置的 priceId
	 */
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	/**
	 * @return price
	 */
	public MaterialPrice getPrice() {
		return price;
	}

	/**
	 * @param price 要设置的 price
	 */
	public void setPrice(MaterialPrice price) {
		this.price = price;
	}

	/**
	 * @return material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material 要设置的 material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getSfkf() {
		return sfkf;
	}

	public void setSfkf(Integer sfkf) {
		this.sfkf = sfkf;
	}


    
    
}