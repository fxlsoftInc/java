package com.jialian.api.domain.para;

import java.io.Serializable;

public class QuotePara implements Serializable{

	private static final long serialVersionUID = -3545963231567852360L;

	private Short type;
	
	private Long id = 0l;//详情ID
	
	private Long itemId = 0l;;//项目ID
	
	private Long mainMaterialId = 0l;;//主材ID
	
	private Long auxiMaterialId = 0l;;//辅材ID
	
	private Long laborId = 0l;;//人工费ID
	
	private Long houseStrCatId = 0l;;//所属空间
	
	private Double projectSize;//工程大小
	
	private String itemUnit;//项目单位
	
	private Double itemPrice;//项目价钱
	
	private String itemName;//项目名称
	
	private Short itemType = (short)1;//项目性质
	
	private String itemCraft;//项目工艺
	
	private Double mainMaterialPrice;//主材价格
	
	private String mainMaterialUnit;//主材单位
	
	private Double auxiMaterialPrice;//辅材价格
	
	private String auxiMaterialUnit;//辅材单位
	
	private Double laborCost;//人工费

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Short getItemType() {
		return itemType;
	}

	public void setItemType(Short itemType) {
		this.itemType = itemType;
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

	public Long getHouseStrCatId() {
		return houseStrCatId;
	}

	public void setHouseStrCatId(Long houseStrCatId) {
		this.houseStrCatId = houseStrCatId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
}
