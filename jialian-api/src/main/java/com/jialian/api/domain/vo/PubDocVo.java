package com.jialian.api.domain.vo;

import java.io.Serializable;

import com.jialian.api.domain.entity.PubDoc;

public class PubDocVo extends PubDoc implements Serializable{

	private static final long serialVersionUID = 6886856672992465707L;
	
	private String permissionName;//权限名称
    
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
}
