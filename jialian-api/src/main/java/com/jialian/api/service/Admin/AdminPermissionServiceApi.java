package com.jialian.api.service.Admin;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.query.AdminPermissionQuery;

public interface AdminPermissionServiceApi {

	/**
	* @Description: 获取角色下的权限
	* @param @param roleId 角色ID
	* @param @return
	* @return ServiceResult<AdminPermission>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午2:03:23
	 */
	public ServiceResult<List<AdminPermission>> getAdminPermissionByRoleId(Long roleId);
	
	/**
	* @Description: 获取权限列表
	* @param @return
	* @return ServiceResult<List<AdminPermission>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月14日 下午3:11:11
	 */
	public ServiceResult<List<AdminPermission>> getAdminPermissionList(AdminPermissionQuery query);
	
	/**
	* @Description: 编辑权限信息
	* @param @param id 权限ID
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午3:05:42
	 */
	public ServiceResult<Object> updateAdminPermission(Long roleId,Long[] permissionIds);
}
