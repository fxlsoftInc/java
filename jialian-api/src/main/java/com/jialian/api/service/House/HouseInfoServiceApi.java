package com.jialian.api.service.House;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseInfo;

public interface HouseInfoServiceApi {

	/**
	* @Description: 房屋信息修改
	* @param @param houseInfo
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午6:41:55
	 */
	public ServiceResult<Object> updateHouseInfo(HouseInfo houseInfo);
	
	/**
	* @Description: 房屋信息添加
	* @param @param houseInfo
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午5:08:34
	 */
	public ServiceResult<Object> addHouseInfo(HouseInfo houseInfo);
}
