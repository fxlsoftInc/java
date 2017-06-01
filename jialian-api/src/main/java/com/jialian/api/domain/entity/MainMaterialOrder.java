package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class MainMaterialOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5699290155393920406L;

	private Long id;

    private String orderNo;

    private String orderWaterNo;

    private Short orderType;

    private Double totalPrice;

    private Date createTime;

    private Date updateTime;

    private Float status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderWaterNo() {
        return orderWaterNo;
    }

    public void setOrderWaterNo(String orderWaterNo) {
        this.orderWaterNo = orderWaterNo == null ? null : orderWaterNo.trim();
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Float getStatus() {
        return status;
    }

    public void setStatus(Float status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}