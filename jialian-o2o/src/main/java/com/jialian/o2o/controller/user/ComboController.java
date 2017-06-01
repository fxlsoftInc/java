package com.jialian.o2o.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.vo.ComboVo;
import com.jialian.api.domain.vo.HouseStyleVo;
import com.jialian.api.domain.vo.SubscribeVo;
import com.jialian.api.service.Combo.ComboServiceApi;
import com.jialian.api.service.House.HouseStyleServiceApi;
import com.jialian.api.service.Subscribe.SubscribeServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.StringUtils;

/**
 *@Description: 套餐信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月15日 下午5:25:43
 *@Version:1.0
 */
@Controller
@RequestMapping("/combo")
public class ComboController {
	
	@Resource
	ComboServiceApi comboServiceApi;
	
	@Resource
	HouseStyleServiceApi houseStyleServiceApi;
	
	@Resource
	SubscribeServiceApi subscribeServiceApi;

	/**
	* @Description: 套餐组合信息列表
	* @param @param id 套餐ID
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 下午5:35:55
	 */
	@RequestMapping("/getComboList.do")
	@ResponseBody
	public JsonResult getComboList(@RequestBean Long id){
		JsonResult jsonResult=new JsonResult();
		//判断参数
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		ServiceResult<List<ComboVo>> result=comboServiceApi.getComboList(id);
		Combo combo = comboServiceApi.selectByPrimaryKey(id);
		jsonResult.setDataObj(result.getData());
		jsonResult.setData(result.getDataMap());
		jsonResult.addData("combo", combo);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		return jsonResult;
	}
	
	
	/**
	* @Description: 装修风格
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月16日 下午4:10:20
	 */
	@RequestMapping("/getHouseStyle.do")
	@ResponseBody
	public JsonResult getHouseStyle(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<HouseStyleVo>> result=houseStyleServiceApi.getHouseStyle();
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	
	/**
	* @Description: 用户预约(套餐)
	* @param @param subscribeVo 预约封装类
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月16日 下午7:17:31
	 */
	@RequestMapping("/userSubscribe.do")
	@ResponseBody
	public JsonResult userSubscribe(@RequestBean SubscribeVo subscribeVo){
		JsonResult jsonResult=new JsonResult();
		if(StringUtils.isStrNull(subscribeVo.getTelphone()) || 
				StringUtils.isStrNull(subscribeVo.getProvince()) || 
				StringUtils.isStrNull(subscribeVo.getCity()) || 
				StringUtils.isStrNull(subscribeVo.getCounty()) ||  
				StringUtils.isStrNull(subscribeVo.getAddress())){
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setComment("参数错误");
			return jsonResult;
		}
		ServiceResult<Subscribe> result=subscribeServiceApi.addSubscribe(subscribeVo);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
