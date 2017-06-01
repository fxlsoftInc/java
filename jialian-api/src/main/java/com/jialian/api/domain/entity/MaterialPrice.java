package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class MaterialPrice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4200777115612983566L;

	private Long id;

    private Long taxClassId;

    private Double priceType;

    private Double specialPrice;

    private Double origPrice;

    private Double price;

    private Double minPrice;

    private Double maxPrice;

    private String currency;

    private Date createTime;

    private Date updateTime;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(Long taxClassId) {
        this.taxClassId = taxClassId;
    }

    public Double getPriceType() {
        return priceType;
    }

    public void setPriceType(Double priceType) {
        this.priceType = priceType;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Double getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(Double origPrice) {
        this.origPrice = origPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
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