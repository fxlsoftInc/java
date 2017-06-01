package com.jialian.api.domain.query;

import java.io.Serializable;

public class AdminPermissionQuery implements Serializable{

	private static final long serialVersionUID = 2618729583759249943L;
	
	private Long roleId;//角色ID

	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
