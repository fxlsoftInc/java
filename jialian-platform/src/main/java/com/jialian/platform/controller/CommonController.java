package com.jialian.platform.controller;

import org.springframework.stereotype.Controller;

/**
 * 
 *@Description:公共方法控制器
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since: 2015年12月2日
 *@Version:1.0
 */
@Controller
public class CommonController {
	
	/**
	* @Description: 获取城市列表
	* @author 刘德宜   wudihaike@vip.qq.com
	* @param zone 地区
	* @return JsonResult
	* @date 2015年11月4日 上午10:58:16
	 */
/*	@RequestMapping("getCityInfo.htm")
	@ResponseBody
	public JsonResult getCityInfoByZone(
			@RequestParam(required = false) String zone){
		JsonResult json = new JsonResult();
		List<CityInfo> list = cityInfoService.getCityInfoByZone(zone);
		json.setData(list);
		json.setOk(true);
		return json;
	}*/
}