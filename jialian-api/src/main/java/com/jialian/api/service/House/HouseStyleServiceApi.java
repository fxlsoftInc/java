package com.jialian.api.service.House;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.vo.HouseStyleVo;

public interface HouseStyleServiceApi {

	/**
	* @Description: 装修风格
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月16日 下午4:10:20
	 */
	ServiceResult<List<HouseStyleVo>> getHouseStyle();
}
