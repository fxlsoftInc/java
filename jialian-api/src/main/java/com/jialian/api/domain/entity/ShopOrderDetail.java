package com.jialian.api.domain.entity;

import java.io.Serializable;

public class ShopOrderDetail  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3486425990815847965L;

	private Long id;

    private Long orderId;

    private String protitle;

    private String propic;

    private Double summoney;

    private Double unitmoney;

    private Integer count;

    private Long proid;

    private Long proAttrId;

    private String materialStandard;

    private String materialAftersale;

    private String materialTechnology;

    private String attributeNo;

    private String attributeName;

    private String attributeUnit;

    private String attributeRemark;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public String getProtitle() {
        return protitle;
    }

    public void setProtitle(String protitle) {
        this.protitle = protitle == null ? null : protitle.trim();
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic == null ? null : propic.trim();
    }

    public Double getSummoney() {
        return summoney;
    }

    public void setSummoney(Double summoney) {
        this.summoney = summoney;
    }

    public Double getUnitmoney() {
        return unitmoney;
    }

    public void setUnitmoney(Double unitmoney) {
        this.unitmoney = unitmoney;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Long getProid() {
        return proid;
    }

    public void setProid(Long proid) {
        this.proid = proid;
    }

    public Long getProAttrId() {
        return proAttrId;
    }

    public void setProAttrId(Long proAttrId) {
        this.proAttrId = proAttrId;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard == null ? null : materialStandard.trim();
    }

    public String getMaterialAftersale() {
        return materialAftersale;
    }

    public void setMaterialAftersale(String materialAftersale) {
        this.materialAftersale = materialAftersale == null ? null : materialAftersale.trim();
    }

    public String getMaterialTechnology() {
        return materialTechnology;
    }

    public void setMaterialTechnology(String materialTechnology) {
        this.materialTechnology = materialTechnology == null ? null : materialTechnology.trim();
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

    public String getAttributeUnit() {
        return attributeUnit;
    }

    public void setAttributeUnit(String attributeUnit) {
        this.attributeUnit = attributeUnit == null ? null : attributeUnit.trim();
    }

    public String getAttributeRemark() {
        return attributeRemark;
    }

    public void setAttributeRemark(String attributeRemark) {
        this.attributeRemark = attributeRemark == null ? null : attributeRemark.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}