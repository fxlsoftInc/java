package com.jialian.platform.controller.caseHouse;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.CaseHouse;
import com.jialian.api.domain.entity.CaseHouseInfo;
import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.query.CaseHouseQuery;
import com.jialian.api.domain.vo.CaseHouseVo;
import com.jialian.api.domain.vo.HouseStyleVo;
import com.jialian.api.service.CaseHouseServiceApi;
import com.jialian.api.service.House.HouseStyleServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.platform.controller.BaseController;

/**
 *@Description: 案例 房信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月11日 下午5:53:08
 *@Version:1.0
 */
@Controller
@RequestMapping("/caseHouse")
public class CaseHouseController extends BaseController{
	
	@Resource
	CaseHouseServiceApi caseHouseServiceApi;
	
	@Resource
	HouseStyleServiceApi houseStyleServiceApi;

	@Resource
	LogServiceApi logService;
	/**
	* @Description: 案例馆信息列表(分页)
	* @param @param query 案例馆查询封装类
	* @param @param request
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月22日 上午10:20:12
	 */
	@RequestMapping("/getCaseHouseList.do")
	@ResponseBody
	public JsonResult getCaseHouseList(@RequestBean CaseHouseQuery query,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<CaseHouseVo>> result= caseHouseServiceApi.getCaseHouseListByQuery(query);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		if(result.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.setDataObj(result.getData());
		}
		return jsonResult;
	}
	
	
	/**
	* @Description: 案例馆详情
	* @param @param id 案例馆ID
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月31日 下午2:20:48
	 */
	@RequestMapping("/getCaseHouse.do")
	@ResponseBody
	public JsonResult getCaseHouse(@RequestBean Long id){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<CaseHouseVo> result=caseHouseServiceApi.getCaseHouse(id,null);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	
	/**
	* @Description: 案例馆添加
	* @param @param caseHouse
	* @param @param caseHouseInfo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月19日 下午2:42:32
	 */
	@RequestMapping("/addCaseHouse.do")
	@ResponseBody
	public JsonResult addCaseHouse(@RequestBean CaseHouse caseHouse,@RequestBean CaseHouseInfo caseHouseInfo,@RequestBean HouseInfo houseInfo,@RequestBean long[] resourceInfoIds){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=caseHouseServiceApi.addCaseHouse(caseHouse, caseHouseInfo, houseInfo,resourceInfoIds);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		AdminUser user = getAdminUser(getRequest());
		logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》案例馆", "添加了新的案例馆");
		return jsonResult;
	}
	
	/**
	* @Description: 案例馆信息修改
	* @param @param caseHouse
	* @param @param caseHouseInfo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月19日 下午3:09:25
	 */
	@RequestMapping("/updateCaseHouse.do")
	@ResponseBody
	public JsonResult updateCaseHouse(@RequestBean CaseHouse caseHouse,@RequestBean CaseHouseInfo caseHouseInfo,@RequestBean HouseInfo houseInfo,@RequestBean List<Object> infos){
		JsonResult jsonResult=new JsonResult();
		List<ResourceInfo> infosList=new ArrayList<ResourceInfo>();
		if(infos==null){
			infos=new ArrayList<Object>();
		}
		//转化为对象信息
		for(int i=0;i<infos.size();i++){
			ResourceInfo resourceInfo = JSONObject.parseObject(infos.get(i).toString(), ResourceInfo.class);
			infosList.add(resourceInfo);
		}
		ServiceResult<Object> result=caseHouseServiceApi.updateCaseHouse(caseHouse, caseHouseInfo,houseInfo,infosList);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		
		AdminUser user = getAdminUser(getRequest());
		logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》案例馆", "修改了案例馆");
		
		return jsonResult;
	}
	
	/**
	* @Description: 获取房屋风格信息
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月9日 下午2:05:46
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
}
