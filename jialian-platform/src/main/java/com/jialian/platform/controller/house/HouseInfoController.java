package com.jialian.platform.controller.house;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.service.House.HouseInfoServiceApi;

/**
 *@Description: 房屋信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月21日 下午6:39:01
 *@Version:1.0
 */
@Controller
@RequestMapping("/houseInfo")
public class HouseInfoController {

	@Resource
	HouseInfoServiceApi houseInfoServiceApi;

	/**
	* @Description: 房屋信息修改
	* @param @param houseInfo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午6:45:01
	 */
	@RequestMapping("/updateHouseInfo.do")
	@ResponseBody
	public JsonResult updateHouseInfo(HouseInfo houseInfo){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=houseInfoServiceApi.updateHouseInfo(houseInfo);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
	/**
	* @Description: 房屋信息添加
	* @param @param houseInfo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午5:07:24
	 */
	@RequestMapping("/addHouseInfo.do")
	@ResponseBody
	public JsonResult addHouseInfo(HouseInfo houseInfo){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=houseInfoServiceApi.addHouseInfo(houseInfo);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
