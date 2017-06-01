package com.jialian.api.domain.vo;

import java.io.Serializable;

/**
 *@Description: 套餐组合
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月15日 下午6:10:52
 *@Version:1.0
 */
public class ComboVo implements Serializable{

	private static final long serialVersionUID = -2369978743107000546L;

	private Long id;//套餐id
	
	private String no;//编号
	
	private String imgPath1;
	
	private String imgPath2;
	
	private Double price;
	
	private String cName;
	
	private String name;
	
	private String type;
	
	private String itemName;
	
	private String workArea;
	
	private String itemIntroduction;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getImgPath1() {
		return imgPath1;
	}
	public void setImgPath1(String imgPath1) {
		this.imgPath1 = imgPath1;
	}
	public String getImgPath2() {
		return imgPath2;
	}
	public void setImgPath2(String imgPath2) {
		this.imgPath2 = imgPath2;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	public String getItemIntroduction() {
		return itemIntroduction;
	}
	public void setItemIntroduction(String itemIntroduction) {
		this.itemIntroduction = itemIntroduction;
	}
	
}
