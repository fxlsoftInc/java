package com.jialian.api.domain.vo;

import java.io.Serializable;

import com.jialian.api.domain.entity.HouseStyle;

public class HouseStyleVo extends HouseStyle implements Serializable{

	private static final long serialVersionUID = -834217389718664451L;
	
	private String path;//图片路径

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
