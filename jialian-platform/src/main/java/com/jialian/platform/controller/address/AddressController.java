package com.jialian.platform.controller.address;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.service.Address.AddressServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.platform.controller.BaseController;

/**
 *@Description: 地址信息控制类
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月21日 下午5:40:11
 *@Version:1.0
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Resource
	AddressServiceApi addressServiceApi;

	@Resource
	LogServiceApi logService;
	/**
	* @Description: 地址信息修改
	* @param @param address
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午5:52:50
	 */
	@RequestMapping("/updateAddress")
	@ResponseBody
	public JsonResult updateAddress(Address address){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=addressServiceApi.updateAddress(address);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		//记入日志数据里
		AdminUser user = getAdminUser(getRequest());
		logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "Address", "修改了地址信息");
		return jsonResult;
	}
}
