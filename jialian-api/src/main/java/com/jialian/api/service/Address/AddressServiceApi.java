package com.jialian.api.service.Address;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;

public interface AddressServiceApi {

	/**
	* @Description: 地址信息修改
	* @param @param address
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午5:50:56
	 */
	public ServiceResult<Object> updateAddress(Address address);

	/**
	 * 添加地址，
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月13日 下午7:13:11
	 *@Version:1.0
	 */
	public Address insertAddress(Address address);
	
	int updateByPrimaryKeySelective(Address record);
}
