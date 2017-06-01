package com.jialian.api.domain.query;

import java.io.Serializable;

public class QuoteOnlineQuery implements Serializable{
	
	private static final long serialVersionUID = -413144547711821951L;
	//建筑面积
	private Double area = 0d;
	//卧室间数
	private Integer wo = 2;
	//客厅
	private Integer ting = 1;
	//厨房间数
	private Integer chu = 1;
	//卫生间间数
	private Integer wei = 1;
	//阳台数
	private Integer yang = 1;
	
	private Double zhuwoArea = 0d;
	
	private Double ciWoArea = 0d;
	
	private Double tingArea = 0d;
	
	private Double chuArea = 0d;
	
	private Double weiArea = 0d;
	
	private Double yangArea = 0d;
	
	//房型，新手房还是二手房 0表示新房，1表示二手房
	private Short type = 0;
	
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Integer getWo() {
		return wo;
	}
	public void setWo(Integer wo) {
		this.wo = wo;
	}
	public Integer getChu() {
		return chu;
	}
	public void setChu(Integer chu) {
		this.chu = chu;
	}
	public Integer getWei() {
		return wei;
	}
	public void setWei(Integer wei) {
		this.wei = wei;
	}
	public Integer getYang() {
		return yang;
	}
	public void setYang(Integer yang) {
		this.yang = yang;
	}
	public Integer getTing() {
		return ting;
	}
	public void setTing(Integer ting) {
		this.ting = ting;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Double getZhuwoArea() {
		return zhuwoArea;
	}
	public void setZhuwoArea(Double zhuwoArea) {
		this.zhuwoArea = zhuwoArea;
	}
	public Double getCiWoArea() {
		return ciWoArea;
	}
	public void setCiWoArea(Double ciWoArea) {
		this.ciWoArea = ciWoArea;
	}
	public Double getTingArea() {
		return tingArea;
	}
	public void setTingArea(Double tingArea) {
		this.tingArea = tingArea;
	}
	public Double getChuArea() {
		return chuArea;
	}
	public void setChuArea(Double chuArea) {
		this.chuArea = chuArea;
	}
	public Double getWeiArea() {
		return weiArea;
	}
	public void setWeiArea(Double weiArea) {
		this.weiArea = weiArea;
	}
	public Double getYangArea() {
		return yangArea;
	}
	public void setYangArea(Double yangArea) {
		this.yangArea = yangArea;
	}
}
