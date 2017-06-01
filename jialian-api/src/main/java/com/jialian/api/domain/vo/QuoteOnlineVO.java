package com.jialian.api.domain.vo;

import java.io.Serializable;

public class QuoteOnlineVO implements Serializable{

	private static final long serialVersionUID = -5498876564034256383L;
	
	private Integer zhuCount = 0;
	
	private Double zhuArea = 0d;
	
	private Integer ciwoCount = 0;
	
	private Double ciwoArea = 0d;
	
	private Integer chuCount = 0;
	
	private Double chuArea =0d;
	
	private Integer ketingCount =0;
	
	private Double ketingArea =0d;
	
	private Integer weiCount =0;
	
	private Double weiArea =0d;
	
	private Integer yangCount =0;
	
	private Double yangArea =0d;

	private SemDecorationOrderVO semDecOrderVO;
	
	public Integer getZhuCount() {
		return zhuCount;
	}

	public void setZhuCount(Integer zhuCount) {
		this.zhuCount = zhuCount;
	}

	public Double getZhuArea() {
		return zhuArea;
	}

	public void setZhuArea(Double zhuArea) {
		this.zhuArea = zhuArea;
	}

	public Integer getCiwoCount() {
		return ciwoCount;
	}

	public void setCiwoCount(Integer ciwoCount) {
		this.ciwoCount = ciwoCount;
	}

	public Double getCiwoArea() {
		return ciwoArea;
	}

	public void setCiwoArea(Double ciwoArea) {
		this.ciwoArea = ciwoArea;
	}

	public Integer getChuCount() {
		return chuCount;
	}

	public void setChuCount(Integer chuCount) {
		this.chuCount = chuCount;
	}

	public Double getChuArea() {
		return chuArea;
	}

	public void setChuArea(Double chuArea) {
		this.chuArea = chuArea;
	}

	public Integer getKetingCount() {
		return ketingCount;
	}

	public void setKetingCount(Integer ketingCount) {
		this.ketingCount = ketingCount;
	}

	public Double getKetingArea() {
		return ketingArea;
	}

	public void setKetingArea(Double ketingArea) {
		this.ketingArea = ketingArea;
	}

	public Integer getWeiCount() {
		return weiCount;
	}

	public void setWeiCount(Integer weiCount) {
		this.weiCount = weiCount;
	}

	public Double getWeiArea() {
		return weiArea;
	}

	public void setWeiArea(Double weiArea) {
		this.weiArea = weiArea;
	}

	public Integer getYangCount() {
		return yangCount;
	}

	public void setYangCount(Integer yangCount) {
		this.yangCount = yangCount;
	}

	public Double getYangArea() {
		return yangArea;
	}

	public void setYangArea(Double yangArea) {
		this.yangArea = yangArea;
	}

	public SemDecorationOrderVO getSemDecOrderVO() {
		return semDecOrderVO;
	}

	public void setSemDecOrderVO(SemDecorationOrderVO semDecOrderVO) {
		this.semDecOrderVO = semDecOrderVO;
	}
	
	
}
