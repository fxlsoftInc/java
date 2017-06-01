package com.jialian.platform.controller.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.query.RoleQuery;
import com.jialian.api.service.Admin.AdminPermissionServiceApi;
import com.jialian.api.service.Admin.AdminRoleServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	AdminRoleServiceApi adminRoleServiceApi;
	
	@Resource
	AdminPermissionServiceApi adminPermissionServiceApi;

	/**
	* @Description: 角色列表(分页)
	* @param @param query
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 上午11:47:21
	 */
	@RequestMapping("/roleList.do")
	@ResponseBody
	public JsonResult roleList(@RequestBean RoleQuery query){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<AdminRole>> result=adminRoleServiceApi.getRoleList(query);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: 角色列表(不分页)
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月12日 下午4:33:34
	 */
	@RequestMapping("getaAdminRole.do")
	@ResponseBody
	public JsonResult getAdminRole(@RequestBean RoleQuery query){
		JsonResult jsonResult=new JsonResult();
		if(query==null){
			query=new RoleQuery();
		}
		ServiceResult<List<AdminRole>> result=adminRoleServiceApi.getAdminRole(query);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: 修改角色信息
	* @param @param role
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午4:30:45
	 */
	@RequestMapping("/updateRole.do")
	@ResponseBody
	public JsonResult updateRole(AdminRole role){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=adminRoleServiceApi.updateRole(role);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
	
	/**
	* @Description: 角色下的权限
	* @param @param roleId 角色id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午1:58:37
	 */
	@RequestMapping("/rolePermission.do")
	@ResponseBody
	public JsonResult rolePermission(Long roleId){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<AdminPermission>> result=adminPermissionServiceApi.getAdminPermissionByRoleId(roleId);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
}
