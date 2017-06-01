package com.jialian.platform.controller.pubDoc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.PubDoc;
import com.jialian.api.domain.vo.PubDocVo;
import com.jialian.api.service.PubDoc.PubDocServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description: 公共文档信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2016年1月19日 下午2:43:37
 *@Version:1.0
 */
@Controller
@RequestMapping("/pubdoc")
public class PubDocController {
	
	@Resource
	PubDocServiceApi pubDocServiceApi;

	/**
	* @Description: 获取公共文档
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午2:44:45
	 */
	@RequestMapping("/getPubDoc.do")
	@ResponseBody
	public JsonResult getPubDoc(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<PubDocVo>> result=pubDocServiceApi.getPubDoc();
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: 文档详情
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午4:25:13
	 */
	@RequestMapping("/getPubDocDetailed.do")
	@ResponseBody
	public JsonResult getPubDocDetailed(@RequestBean Long id){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<PubDocVo> result=pubDocServiceApi.getPubDocDetailed(id);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: 编辑文档
	* @param @param pubDoc
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午4:53:55
	 */
	@RequestMapping("/editPubDoc.do")
	@ResponseBody
	public JsonResult editPubDoc(@RequestBean PubDoc pubDoc){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=pubDocServiceApi.updatePubDoc(pubDoc);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
}
