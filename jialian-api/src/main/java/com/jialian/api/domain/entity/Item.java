package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1888465992963403410L;

	private Long id;

    private String itemNo;

    private String itemName;

    private Long houseStructureCategoryId;
    
    private HouseStructureCategory houseStructureCategory;

    private String itemCraft;

    private Short itemType;

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

    public Long getHouseStructureCategoryId() {
        return houseStructureCategoryId;
    }

    public void setHouseStructureCategoryId(Long houseStructureCategoryId) {
        this.houseStructureCategoryId = houseStructureCategoryId;
    }

    public String getItemCraft() {
        return itemCraft;
    }

    public void setItemCraft(String itemCraft) {
        this.itemCraft = itemCraft == null ? null : itemCraft.trim();
    }

    public Short getItemType() {
        return itemType;
    }

    public void setItemType(Short itemType) {
        this.itemType = itemType;
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

	public HouseStructureCategory getHouseStructureCategory() {
		return houseStructureCategory;
	}

	public void setHouseStructureCategory(HouseStructureCategory houseStructureCategory) {
		this.houseStructureCategory = houseStructureCategory;
	}
}