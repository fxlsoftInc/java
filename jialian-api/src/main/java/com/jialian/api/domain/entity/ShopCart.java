package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class ShopCart  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8126789010034862220L;

	private Long id;

    private Long userId;

    private Long proId;

    private Long proAttrId;

    private Integer count;

    private String remark;

    private Date createTime;
    
    private Material material;
    
    private MaterialAttribute materialAttribute;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Long getProAttrId() {
        return proAttrId;
    }

    public void setProAttrId(Long proAttrId) {
        this.proAttrId = proAttrId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	/**
	 * @return materialAttribute
	 */
	public MaterialAttribute getMaterialAttribute() {
		return materialAttribute;
	}

	/**
	 * @param materialAttribute 要设置的 materialAttribute
	 */
	public void setMaterialAttribute(MaterialAttribute materialAttribute) {
		this.materialAttribute = materialAttribute;
	}
    
    
}