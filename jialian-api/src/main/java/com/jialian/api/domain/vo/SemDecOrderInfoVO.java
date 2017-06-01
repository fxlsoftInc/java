package com.jialian.api.domain.vo;

import java.io.Serializable;

public class SemDecOrderInfoVO implements Serializable{

	private static final long serialVersionUID = -7703410397340549122L;

	private Short type;//新房，二手房
	
	private Long id;
	
	private Long itemId;
	
	private Long mainMaterialId;
	
	private Long auxiMaterialId;
	
	private Long laborId;
	
	private Long houseStrCatId;
	
	private Double projectSize;
	
	private String itemUnit;
	
	private Double itemPrice;
	
	private Double totalMainMaterialPrice;
	
	private Double totalAuxiMaterialPrice;
	
	private Double totalLaborCost;
	
	private String itemName;
	
	private Short itemType;
	
	private String itemCraft;
	
	private Double mainMaterialPrice;
	
	private String mainMaterialUnit;
	
	private Double auxiMaterialPrice;
	
	private String auxiMaterialUnit;
	
	private Double laborCost;
	
	private String houStrCatName;
	
	private Short houStrCatType;
	
	private String houStrName;
	
	private Short houStrType;

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Double getProjectSize() {
		return projectSize;
	}

	public void setProjectSize(Double projectSize) {
		this.projectSize = projectSize;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getTotalMainMaterialPrice() {
		return totalMainMaterialPrice;
	}

	public void setTotalMainMaterialPrice(Double totalMainMaterialPrice) {
		this.totalMainMaterialPrice = totalMainMaterialPrice;
	}

	public Double getTotalAuxiMaterialPrice() {
		return totalAuxiMaterialPrice;
	}

	public void setTotalAuxiMaterialPrice(Double totalAuxiMaterialPrice) {
		this.totalAuxiMaterialPrice = totalAuxiMaterialPrice;
	}

	public Double getTotalLaborCost() {
		return totalLaborCost;
	}

	public void setTotalLaborCost(Double totalLaborCost) {
		this.totalLaborCost = totalLaborCost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCraft() {
		return itemCraft;
	}

	public void setItemCraft(String itemCraft) {
		this.itemCraft = itemCraft;
	}

	public Double getMainMaterialPrice() {
		return mainMaterialPrice;
	}

	public void setMainMaterialPrice(Double mainMaterialPrice) {
		this.mainMaterialPrice = mainMaterialPrice;
	}

	public String getMainMaterialUnit() {
		return mainMaterialUnit;
	}

	public void setMainMaterialUnit(String mainMaterialUnit) {
		this.mainMaterialUnit = mainMaterialUnit;
	}

	public Double getAuxiMaterialPrice() {
		return auxiMaterialPrice;
	}

	public void setAuxiMaterialPrice(Double auxiMaterialPrice) {
		this.auxiMaterialPrice = auxiMaterialPrice;
	}

	public String getAuxiMaterialUnit() {
		return auxiMaterialUnit;
	}

	public void setAuxiMaterialUnit(String auxiMaterialUnit) {
		this.auxiMaterialUnit = auxiMaterialUnit;
	}

	public Double getLaborCost() {
		return laborCost;
	}

	public void setLaborCost(Double laborCost) {
		this.laborCost = laborCost;
	}

	public String getHouStrCatName() {
		return houStrCatName;
	}

	public void setHouStrCatName(String houStrCatName) {
		this.houStrCatName = houStrCatName;
	}

	public String getHouStrName() {
		return houStrName;
	}

	public void setHouStrName(String houStrName) {
		this.houStrName = houStrName;
	}

	public Short getHouStrType() {
		return houStrType;
	}

	public void setHouStrType(Short houStrType) {
		this.houStrType = houStrType;
	}

	public Short getHouStrCatType() {
		return houStrCatType;
	}

	public void setHouStrCatType(Short houStrCatType) {
		this.houStrCatType = houStrCatType;
	}

	public Short getItemType() {
		return itemType;
	}

	public void setItemType(Short itemType) {
		this.itemType = itemType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getMainMaterialId() {
		return mainMaterialId;
	}

	public void setMainMaterialId(Long mainMaterialId) {
		this.mainMaterialId = mainMaterialId;
	}

	public Long getAuxiMaterialId() {
		return auxiMaterialId;
	}

	public void setAuxiMaterialId(Long auxiMaterialId) {
		this.auxiMaterialId = auxiMaterialId;
	}

	public Long getLaborId() {
		return laborId;
	}

	public void setLaborId(Long laborId) {
		this.laborId = laborId;
	}

	public Long getHouseStrCatId() {
		return houseStrCatId;
	}

	public void setHouseStrCatId(Long houseStrCatId) {
		this.houseStrCatId = houseStrCatId;
	}
}
