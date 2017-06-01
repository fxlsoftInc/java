package com.jialian.api.domain.para;

import java.io.Serializable;

public class ComboInfoPara implements Serializable{

	private static final long serialVersionUID = 5587139347088163971L;

	private Long id;
	
	private Long comboId;
	
	private Long itemId;
	
	private String workArea;
	
	private String itemIntroduction;
	
	private String itemName;
	
	private Long hStrCatId;

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long gethStrCatId() {
		return hStrCatId;
	}

	public void sethStrCatId(Long hStrCatId) {
		this.hStrCatId = hStrCatId;
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
}
