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
import com.jialian.api.domain.query.CaseHouseQuery;
import com.jialian.api.domain.vo.CaseHouseVo;
import com.jialian.api.service.CaseHouseServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description: 案例 房信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月11日 下午5:53:08
 *@Version:1.0
 */
@Controller
@RequestMapping("/caseHouse")
public class CaseHouseController {
	
	@Resource
	CaseHouseServiceApi caseHouseServiceApi;

	/**
	 * @Description: 案例馆列表（分页）
	 * @param: @param query
	 * @param: @param request
	 * @param: @return   
	 * @return: JsonResult   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
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
	* @param @param id 案例馆id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月17日 下午2:03:05
	 */
	@RequestMapping("/caseHouseDetails.do")
	@ResponseBody
	public JsonResult caseHouseDetails(@RequestBean Long id,@RequestBean Long userId){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<CaseHouseVo> result=caseHouseServiceApi.getCaseHouse(id,userId);
		jsonResult.setDataObj(result.getData());
		jsonResult.setSuccess(result.isOk());
		return jsonResult;
	}
	
	
	/**
	* @Description: 案例馆收藏
	* @param @param id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月28日 下午5:06:07
	 */
	@RequestMapping("/collecCaseHouse.do")
	@ResponseBody
	public JsonResult collecCaseHouse(@RequestBean Long id,@RequestBean Long userId){
		JsonResult jsonResult=new JsonResult();
		if(id==null || userId==null){
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		ServiceResult<Object> result=caseHouseServiceApi.addCollecCaseHouse(userId,id);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
