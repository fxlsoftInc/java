package com.jialian.platform.controller.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.query.AdminPermissionQuery;
import com.jialian.api.service.Admin.AdminPermissionServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description: 权限信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2016年1月5日 下午3:04:06
 *@Version:1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Resource
	AdminPermissionServiceApi adminPermissionServiceApi;

	/**
	* @Description: 获取权限信息
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月14日 下午3:16:57
	 */
	@RequestMapping("/getAdminPermissionList.do")
	@ResponseBody
	public JsonResult getAdminPermissionList(@RequestBean AdminPermissionQuery query){
		System.out.println("--"+query.getRoleId());
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<AdminPermission>> result=adminPermissionServiceApi.getAdminPermissionList(query);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.addData("list", result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: 编辑权限
	* @param @param id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月5日 下午3:03:16
	 */
	@RequestMapping("/updatePermissionArray.do")
	@ResponseBody
	public JsonResult updatePermission(@RequestBean Long roleId,@RequestBean Long[] permissionIds){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=adminPermissionServiceApi.updateAdminPermission(roleId, permissionIds);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
