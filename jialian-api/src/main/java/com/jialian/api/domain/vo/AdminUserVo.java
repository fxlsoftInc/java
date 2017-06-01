package com.jialian.api.domain.vo;

import java.io.Serializable;

public class AdminUserVo implements Serializable{

	/** serialVersionUID:序列号 */
	
	private static final long serialVersionUID = 1379117641793933605L;
    
	private Long userId;//后台用户id
	
	private Long roleId;//角色Id
	
	private String roleName;//用户角色
	
	private String userName;//用户ming 
	
	private String userStatus;//用户状态(0为删除状态，1为启用状态，2为停用状态)
	
	private String telephone;//密保手机

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
