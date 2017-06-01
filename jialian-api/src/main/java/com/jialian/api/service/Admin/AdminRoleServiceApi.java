package com.jialian.api.service.Admin;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.query.RoleQuery;

public interface AdminRoleServiceApi {

	/**
	* @Description: 角色列表(分页)
	* @param @param query
	* @param @return
	* @return ServiceResult<Page<AdminRole>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 上午11:13:32
	 */
	public ServiceResult<Page<AdminRole>> getRoleList(RoleQuery query);
	
	/**
	* @Description: 角色列表(不分页)
	* @param @return
	* @return ServiceResult<List<AdminRole>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月12日 下午4:35:31
	 */
	public ServiceResult<List<AdminRole>> getAdminRole(RoleQuery query);
	
	/**
	* @Description: 修改角色信息
	* @param @param role
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午4:27:50
	 */
	public ServiceResult<Object> updateRole(AdminRole role);
}
