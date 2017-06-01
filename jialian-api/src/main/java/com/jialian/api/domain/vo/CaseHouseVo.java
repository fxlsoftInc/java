package com.jialian.api.domain.vo;

import java.io.Serializable;
import java.util.List;

import com.jialian.api.domain.entity.ResourceInfo;

public class CaseHouseVo implements Serializable{

	private static final long serialVersionUID = -7825661012763293965L;

	private Long id;//编号
	
	private Long houseInfoId;//房屋信息编号
	
	private Short houseType;//房型
	
	private String buildingTypeName;//空间
	
	private Short caseStyleId;//风格ID
	
	private String styleTypeName;//风格
	
	private String houseColor;//颜色
	
	private String caseHose;//简介

	private Short caseStatus;//发布状态
	
	private Short status;//是否推到首页
	
	private String caseDesignIntroduction;//案例简介
	
	private String path;//图片路径
	
	private String name;//图片名称
	
	private String extension;//拓展名
	
	private Long collectStatus=0l;//收藏状态
	
    private List<ResourceInfo> imgs;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Short getHouseType() {
		return houseType;
	}
	public void setHouseType(Short houseType) {
		this.houseType = houseType;
	}
	public String getBuildingTypeName() {
		return buildingTypeName;
	}
	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}
	public String getStyleTypeName() {
		return styleTypeName;
	}
	public void setStyleTypeName(String styleTypeName) {
		this.styleTypeName = styleTypeName;
	}
	public String getHouseColor() {
		return houseColor;
	}
	public void setHouseColor(String houseColor) {
		this.houseColor = houseColor;
	}
	public String getCaseHose() {
		return caseHose;
	}
	public void setCaseHose(String caseHose) {
		this.caseHose = caseHose;
	}
	public Short getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(Short caseStatus) {
		this.caseStatus = caseStatus;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<ResourceInfo> getImgs() {
		return imgs;
	}
	public void setImgs(List<ResourceInfo> imgs) {
		this.imgs = imgs;
	}
	public Short getCaseStyleId() {
		return caseStyleId;
	}
	public void setCaseStyleId(Short caseStyleId) {
		this.caseStyleId = caseStyleId;
	}
	public String getCaseDesignIntroduction() {
		return caseDesignIntroduction;
	}
	public void setCaseDesignIntroduction(String caseDesignIntroduction) {
		this.caseDesignIntroduction = caseDesignIntroduction;
	}
	public Long getHouseInfoId() {
		return houseInfoId;
	}
	public void setHouseInfoId(Long houseInfoId) {
		this.houseInfoId = houseInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Long getCollectStatus() {
		return collectStatus;
	}
	public void setCollectStatus(Long collectStatus) {
		this.collectStatus = collectStatus;
	}
}
