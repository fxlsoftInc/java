package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class LaborCost implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4619079396150872642L;

	private Long id;

    private String name;

    private Double laborCost;

    private String currency;

    private Long adressId;

    private Long decorationItemId;

    /**
     * 
     */
    private Date createTime;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(Double laborCost) {
        this.laborCost = laborCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getAdressId() {
        return adressId;
    }

    public void setAdressId(Long adressId) {
        this.adressId = adressId;
    }

    public Long getDecorationItemId() {
        return decorationItemId;
    }

    public void setDecorationItemId(Long decorationItemId) {
        this.decorationItemId = decorationItemId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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