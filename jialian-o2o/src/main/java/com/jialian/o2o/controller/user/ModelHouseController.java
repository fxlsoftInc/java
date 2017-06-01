package com.jialian.o2o.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.query.ModelHouseQuery;
import com.jialian.api.domain.vo.ModelHouseVo;
import com.jialian.api.service.ModelHouseServiceApi;
import com.jialian.api.service.Subscribe.SubscribeServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description:样板房信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月11日 下午3:55:17
 *@Version:1.0
 */
@Controller
@RequestMapping("/modelHouse")
public class ModelHouseController {
	
	@Resource
	ModelHouseServiceApi modelHouseServiceApi;
	
	@Resource
	SubscribeServiceApi subscribeServiceApi;

	/**
	 * @Description: 样板房信息列表 (分页)
	 * @param: @param query 查询封装
	 * @param: @param request
	 * @param: @return   
	 * @return: JsonResult   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	@RequestMapping("/getModelHouseList.do")
	@ResponseBody
	public JsonResult getModelHouseList(
		@RequestBean ModelHouseQuery query,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<ModelHouseVo>> result= modelHouseServiceApi.getModelHouseListByQuery(query);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		if(result.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.addData("page", result.getData());
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setMessage("没有数据了");
		}
		return jsonResult;
	}
	
	@RequestMapping("/subModelHouse.do")
	@ResponseBody
	public JsonResult subModelHouse(@RequestBean Subscribe subscribe){
		JsonResult result = new JsonResult();
		if(subscribe.getUserid() == null || subscribe.getSubFromId() == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		subscribe.setType((short)2);
		subscribe.setStatus((short)1);
		subscribe.setRemark("样品房预约");
		int flag = subscribeServiceApi.insertSelective(subscribe);
		if(flag > 0){
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("预约成功");
			return result;
		}else{
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE);
			result.setMessage("预约失败");
			return result;
		}
	}
}
