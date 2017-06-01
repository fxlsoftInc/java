package com.jialian.api.service.Subscribe;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.vo.SubscribeVo;

public interface SubscribeServiceApi {

	/**
	* @Description: 用户预约(套餐)
	* @param @param subscribeVo
	* @param @return
	* @return ServiceResult<Subscribe>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月17日 上午10:43:11
	 */
	ServiceResult<Subscribe> addSubscribe(SubscribeVo subscribeVo);
	
	int insertSelective(Subscribe record);
}
