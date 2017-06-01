package com.jialian.api.service;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.vo.HomeVo;

public interface HomeServiceApi {

	/**
	* @Description: 首页信息采集
	* @param @return
	* @return ServiceResult<HomeVo>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 下午2:19:19
	 */
	ServiceResult<HomeVo> getHomeMes();
}
