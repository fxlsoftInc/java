package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShopOrder  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2589262775508555726L;

	private Long id;

    private String orderNo;

    private Long userId;

    private Double totalAmt;

    private Double discountAmt;

    private Double orderAmt;

    private Double prepayAmt;

    private Double payedAmt;

    private Short orderStatus;

    private Date confirmTime;

    private Date completeTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Short ispinkageAmt;

    private Double postage;

    private String useraddr;

    private Long addrid;

    private Short paytype;

    private Short status;
    
    private Short sendStatus;
    
    private Short isvalid;
    
    private String chargeId;
    
    private List<ShopOrderDetail> shopOrderDetails;
    
    private String linktel;
    
    private String expressage;
    
    private String expressno;
    
    private String consignee;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Double orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Double getPrepayAmt() {
        return prepayAmt;
    }

    public void setPrepayAmt(Double prepayAmt) {
        this.prepayAmt = prepayAmt;
    }

    public Double getPayedAmt() {
        return payedAmt;
    }

    public void setPayedAmt(Double payedAmt) {
        this.payedAmt = payedAmt;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getIspinkageAmt() {
        return ispinkageAmt;
    }

    public void setIspinkageAmt(Short ispinkageAmt) {
        this.ispinkageAmt = ispinkageAmt;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr == null ? null : useraddr.trim();
    }

    public Long getAddrid() {
        return addrid;
    }

    public void setAddrid(Long addrid) {
        this.addrid = addrid;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	/**
	 * @return sendStatus
	 */
	public Short getSendStatus() {
		return sendStatus;
	}

	/**
	 * @param sendStatus 要设置的 sendStatus
	 */
	public void setSendStatus(Short sendStatus) {
		this.sendStatus = sendStatus;
	}

	/**
	 * @return isvalid
	 */
	public Short getIsvalid() {
		return isvalid;
	}

	/**
	 * @param isvalid 要设置的 isvalid
	 */
	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	/**
	 * @return chargeId
	 */
	public String getChargeId() {
		return chargeId;
	}

	/**
	 * @param chargeId 要设置的 chargeId
	 */
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	/**
	 * @return shopOrderDetails
	 */
	public List<ShopOrderDetail> getShopOrderDetails() {
		return shopOrderDetails;
	}

	/**
	 * @param shopOrderDetails 要设置的 shopOrderDetails
	 */
	public void setShopOrderDetails(List<ShopOrderDetail> shopOrderDetails) {
		this.shopOrderDetails = shopOrderDetails;
	}

	/**
	 * @return linktel
	 */
	public String getLinktel() {
		return linktel;
	}

	/**
	 * @param linktel 要设置的 linktel
	 */
	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	/**
	 * @return expressage
	 */
	public String getExpressage() {
		return expressage;
	}

	/**
	 * @param expressage 要设置的 expressage
	 */
	public void setExpressage(String expressage) {
		this.expressage = expressage;
	}

	/**
	 * @return expressno
	 */
	public String getExpressno() {
		return expressno;
	}

	/**
	 * @param expressno 要设置的 expressno
	 */
	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	/**
	 * @return consignee
	 */
	public String getConsignee() {
		return consignee;
	}

	/**
	 * @param consignee 要设置的 consignee
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

    
    
}