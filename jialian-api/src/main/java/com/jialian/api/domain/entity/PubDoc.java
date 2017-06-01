package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *@Description: 公共文档
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2016年1月19日 下午2:00:07
 *@Version:1.0
 */
public class PubDoc implements Serializable{

	private static final long serialVersionUID = -1312621128782105216L;

	private Long id;//ID
	
	private Long permissionId;//权限ID
	
	private Date createTime;//创建时间
	
	private Date updateTime;//编辑时间
	
	private String webUrl;//对应链接地址
	
	private String docDetailed;//文档详情
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
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
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getDocDetailed() {
		return docDetailed;
	}
	public void setDocDetailed(String docDetailed) {
		this.docDetailed = docDetailed;
	}
	
}
