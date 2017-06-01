package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class MainMaterialOrderInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4359237327996451796L;

	private Long id;

    private Long mainMaterialOrderId;

    private Long houseStructureCategoryId;

    private Long materialId;

    private Double materialValue;

    private String materialUnit;

    private Float materialPrice;

    private Float materialTotalPrice;

    private String currency;

    private Float materialDiscount;

    private Float materialActualTotalPrice;

    private String materialCraft;

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

    public Long getMainMaterialOrderId() {
        return mainMaterialOrderId;
    }

    public void setMainMaterialOrderId(Long mainMaterialOrderId) {
        this.mainMaterialOrderId = mainMaterialOrderId;
    }

    public Long getHouseStructureCategoryId() {
        return houseStructureCategoryId;
    }

    public void setHouseStructureCategoryId(Long houseStructureCategoryId) {
        this.houseStructureCategoryId = houseStructureCategoryId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Double getMaterialValue() {
        return materialValue;
    }

    public void setMaterialValue(Double materialValue) {
        this.materialValue = materialValue;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit == null ? null : materialUnit.trim();
    }

    public Float getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Float materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Float getMaterialTotalPrice() {
        return materialTotalPrice;
    }

    public void setMaterialTotalPrice(Float materialTotalPrice) {
        this.materialTotalPrice = materialTotalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Float getMaterialDiscount() {
        return materialDiscount;
    }

    public void setMaterialDiscount(Float materialDiscount) {
        this.materialDiscount = materialDiscount;
    }

    public Float getMaterialActualTotalPrice() {
        return materialActualTotalPrice;
    }

    public void setMaterialActualTotalPrice(Float materialActualTotalPrice) {
        this.materialActualTotalPrice = materialActualTotalPrice;
    }

    public String getMaterialCraft() {
        return materialCraft;
    }

    public void setMaterialCraft(String materialCraft) {
        this.materialCraft = materialCraft == null ? null : materialCraft.trim();
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