package com.jialian.api.service.Order;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.query.OrderQuery;
import com.jialian.api.domain.vo.SubLookVo;
import com.jialian.api.domain.vo.SubOrderInfoVO;

public interface SubOrderServiceApi {

	/**
	* @Description: 预约参观订单列表
	* @param @param query
	* @param @return
	* @return ServiceResult<Page<SubLookVo>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月20日 下午4:09:42
	 */
	ServiceResult<Page<SubLookVo>> getSubLookOrderList(OrderQuery query);
	
	/**
	* @Description: 预约订单 修改
	* @param @param subOrder
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月23日 下午1:41:10
	 */
    ServiceResult<Object> updateSubscribeOrderStatus(SubOrder subOrder);
	
	SubOrderInfoVO selectByOrderNo(String orderNo);
	
}
