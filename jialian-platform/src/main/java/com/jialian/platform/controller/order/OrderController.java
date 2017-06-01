package com.jialian.platform.controller.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.query.OrderQuery;
import com.jialian.api.domain.vo.SubLookVo;
import com.jialian.api.service.Order.SubOrderServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	SubOrderServiceApi subOrderServiceApi;
	
	/**
	* @Description: 预约参观订单列表
	* @param @param query
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月20日 下午4:36:17
	 */
	@RequestMapping("/getSubLookOrderList.do")
	@ResponseBody
	public JsonResult getSubLookOrderList(@RequestBean OrderQuery query){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<SubLookVo>> result=subOrderServiceApi.getSubLookOrderList(query);
		jsonResult.setStateCode(result.getMsgCode());
		if(result.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.setDataObj(result.getData());
		}
		return jsonResult;
	}

	/**
	* @Description: 修改预约订单状态
	* @param @param id
	* @param @param status
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月31日 下午4:33:56
	 */
	@RequestMapping("/updateSubscribeOrderStatus.do")
	@ResponseBody
	public JsonResult updateSubscribeOrderStatus(@RequestBean SubOrder subOrder){
		JsonResult jsonResult=new  JsonResult();
		ServiceResult<Object> result=subOrderServiceApi.updateSubscribeOrderStatus(subOrder);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
