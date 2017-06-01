package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SemiDecorationOrderInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4256166284332252884L;

	private Long id;

	private Long semiDecOrderId;
	
    private String itemNo;

    private String itemName;

    private Long itemId;

    private Double itemProjectSize;

    private String itemProjectUnit;

    private Double mainMaterialPrice;

    private Long itemMainMaterialId;

    private Double auxiliaryMaterialPrice;

    private Long itemAuxiliaryMaterialId;

    private Double laborCost;

    private String currency;

    private Long laborCostId;

    private Double itemPrice;

    private String itemCraft;

    private Date createTime;

    private Date updateTime;

    private Short itemStatus;

    private Short itemType;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getItemProjectSize() {
        return itemProjectSize;
    }

    public void setItemProjectSize(Double itemProjectSize) {
        this.itemProjectSize = itemProjectSize;
    }

    public String getItemProjectUnit() {
        return itemProjectUnit;
    }

    public void setItemProjectUnit(String itemProjectUnit) {
        this.itemProjectUnit = itemProjectUnit == null ? null : itemProjectUnit.trim();
    }

    public Double getMainMaterialPrice() {
        return mainMaterialPrice;
    }

    public void setMainMaterialPrice(Double mainMaterialPrice) {
        this.mainMaterialPrice = mainMaterialPrice;
    }

    public Long getItemMainMaterialId() {
        return itemMainMaterialId;
    }

    public void setItemMainMaterialId(Long itemMainMaterialId) {
        this.itemMainMaterialId = itemMainMaterialId;
    }

    public Double getAuxiliaryMaterialPrice() {
        return auxiliaryMaterialPrice;
    }

    public void setAuxiliaryMaterialPrice(Double auxiliaryMaterialPrice) {
        this.auxiliaryMaterialPrice = auxiliaryMaterialPrice;
    }

    public Long getItemAuxiliaryMaterialId() {
        return itemAuxiliaryMaterialId;
    }

    public void setItemAuxiliaryMaterialId(Long itemAuxiliaryMaterialId) {
        this.itemAuxiliaryMaterialId = itemAuxiliaryMaterialId;
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

    public Long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(Long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCraft() {
        return itemCraft;
    }

    public void setItemCraft(String itemCraft) {
        this.itemCraft = itemCraft == null ? null : itemCraft.trim();
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

    public Short getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Short itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Short getItemType() {
        return itemType;
    }

    public void setItemType(Short itemType) {
        this.itemType = itemType;
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

	public Long getSemiDecOrderId() {
		return semiDecOrderId;
	}

	public void setSemiDecOrderId(Long semiDecOrderId) {
		this.semiDecOrderId = semiDecOrderId;
	}
}