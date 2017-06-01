package com.jialian.platform.controller.resourceInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/resourceInfo")
public class ResourceInfoController {

	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	
	/**
	 * @Description:修改图片关系参数
	 * @Param:@param resourceInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月8日下午5:55:17
	 * @Version:1.0
	 */
	@RequestMapping("/updateResourceInfoById.do")
	@ResponseBody
	public JsonResult updateResourceInfoById(@RequestBean ResourceInfo resourceInfo){
		JsonResult result = new JsonResult();
		if(resourceInfo.getId() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}
	
	/**
	 * @Description:删除照片
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日上午11:49:00
	 * @Version:1.0
	 */
	@RequestMapping("/deleteResourceInfoById.do")
	@ResponseBody
	public JsonResult deleteResourceInfoById(Long id){
		JsonResult result = new JsonResult();
		if(id == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		ResourceInfo resourceInfo = new ResourceInfo();
		resourceInfo.setId(id);
		resourceInfo.setNo("PIC" + idWorker.nextId());
		resourceInfo.setObjectId(0l);
		resourceInfo.setObjectType((short)0);
		int flag = resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
		if(flag > 0){
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("删除成功");
		}else{
			result.setStateCode(Const.ERROR_RECORD_DELETED);
			result.setSuccess(false);
			result.setMessage("删除失败");
		}
		return result;
	}

	/**
	 * @Description:替换照片
	 * @Param:@param resourceInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日上午11:52:07
	 * @Version:1.0
	 */
	@RequestMapping("/exchangeResourceInfo.do")
	@ResponseBody
	public JsonResult exchangeResourceInfo(@RequestBean ResourceInfoReplacePara resourceInfo) {
		JsonResult result = new JsonResult();
		if (resourceInfo == null || resourceInfo.getFromId() == null || resourceInfo.getFromNo() == null
				|| resourceInfo.getTargetId() == null || resourceInfo.getTargetNo() == null) {
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		ResourceInfo resInf = new ResourceInfo();
		resInf.setId(resourceInfo.getFromId());
		resInf.setNo(resourceInfo.getTargetNo());
		resourceInfoService.updateByPrimaryKeySelective(resInf);
		resInf.setId(resourceInfo.getTargetId());
		resInf.setNo(resourceInfo.getFromNo());
		resourceInfoService.updateByPrimaryKeySelective(resInf);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("保存成功");
		return result;
	}
}
