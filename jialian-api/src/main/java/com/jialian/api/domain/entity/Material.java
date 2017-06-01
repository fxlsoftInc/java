package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Material implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4675955228686628024L;

	private Long id;

    private String materialNo;

    private String materialName;

    private String brandName;

    private Double materialPrice;

    private String currency;

    private Long materialPriceId;

    private String materialUnit;

    private String materialProperty;

    private Short materialType;

    private Date createTime;

    private String remark;

    private Short status;

    private String materialTechnology;

    private MaterialPrice price;
    
    private List<MaterialAttribute> attributeList;
    
    private List<ResourceInfo> resourceInfoList;
    
    private Short prodCategory;

    private String materialAftersale;

    private String materialStandard;
    
    private Double postage;
    
    private Integer fbzt;
    
    private Date fbtime;
    
    private Long prodTypeId;
    
    private ShopProdType shopProdType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Double getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Double materialPrice) {
        this.materialPrice = materialPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getMaterialPriceId() {
        return materialPriceId;
    }

    public void setMaterialPriceId(Long materialPriceId) {
        this.materialPriceId = materialPriceId;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit == null ? null : materialUnit.trim();
    }

    public String getMaterialProperty() {
        return materialProperty;
    }

    public void setMaterialProperty(String materialProperty) {
        this.materialProperty = materialProperty == null ? null : materialProperty.trim();
    }

    public Short getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Short materialType) {
        this.materialType = materialType;
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

    public String getMaterialTechnology() {
        return materialTechnology;
    }

    public void setMaterialTechnology(String materialTechnology) {
        this.materialTechnology = materialTechnology == null ? null : materialTechnology.trim();
    }

	public List<MaterialAttribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<MaterialAttribute> attributeList) {
		this.attributeList = attributeList;
	}

	public MaterialPrice getPrice() {
		return price;
	}

	public void setPrice(MaterialPrice price) {
		this.price = price;
	}

	public List<ResourceInfo> getResourceInfoList() {
		return resourceInfoList;
	}

	public void setResourceInfoList(List<ResourceInfo> resourceInfoList) {
		this.resourceInfoList = resourceInfoList;
	}

	/**
	 * @return prodCategory
	 */
	public Short getProdCategory() {
		return prodCategory;
	}

	/**
	 * @param prodCategory 要设置的 prodCategory
	 */
	public void setProdCategory(Short prodCategory) {
		this.prodCategory = prodCategory;
	}

	/**
	 * @return materialAftersale
	 */
	public String getMaterialAftersale() {
		return materialAftersale;
	}

	/**
	 * @param materialAftersale 要设置的 materialAftersale
	 */
	public void setMaterialAftersale(String materialAftersale) {
		this.materialAftersale = materialAftersale;
	}

	/**
	 * @return materialStandard
	 */
	public String getMaterialStandard() {
		return materialStandard;
	}

	/**
	 * @param materialStandard 要设置的 materialStandard
	 */
	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}

	/**
	 * @return postage
	 */
	public Double getPostage() {
		return postage;
	}

	/**
	 * @param postage 要设置的 postage
	 */
	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Integer getFbzt() {
		return fbzt;
	}

	public void setFbzt(Integer fbzt) {
		this.fbzt = fbzt;
	}

	public Date getFbtime() {
		return fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}

	/**
	 * @return prodTypeId
	 */
	public Long getProdTypeId() {
		return prodTypeId;
	}

	/**
	 * @param prodTypeId 要设置的 prodTypeId
	 */
	public void setProdTypeId(Long prodTypeId) {
		this.prodTypeId = prodTypeId;
	}

	/**
	 * @return shopProdType
	 */
	public ShopProdType getShopProdType() {
		return shopProdType;
	}

	/**
	 * @param shopProdType 要设置的 shopProdType
	 */
	public void setShopProdType(ShopProdType shopProdType) {
		this.shopProdType = shopProdType;
	}
	
	
}