package com.jialian.platform.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.platform.controller.BaseController;

/**
 * 首页相关操作控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2016年1月12日 上午11:54:59
 *@Version:1.0
 */
@Controller
@RequestMapping("home")
public class HomePageController extends BaseController{
    
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private LogServiceApi logService;
	/**
	 * 首页大图查询
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月12日 下午3:55:46
	 *@Version:1.0
	 */
	@RequestMapping("/selectResourceInfoByHomeType.do")
	@ResponseBody
	public JsonResult selectResourceInfoByHomeType(){
		JsonResult jsonResult = new JsonResult();
		Where where = new Where();
		where.setOrderByClause("no");
		Criteria criteria = where.createCriteria();
		List<Object> list = new ArrayList<Object>();
		list.add("SY0001");
		list.add("SY0002");
		list.add("SY0003");
		list.add("SY0004");
		list.add("SY0005");
		
		criteria.andIn("no", list);
		List<ResourceInfo> result = resourceInfoService.selectByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		if (result != null) {
			jsonResult.addData("homeImgList", result);
			jsonResult.setDataObj(result.size());
		} else {
			jsonResult.setDataObj(0);
		}
		return jsonResult;
	}
	
	
	/**
	 * 合作商户查询
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月12日 下午3:55:11
	 *@Version:1.0
	 */
	@RequestMapping("/selectResouceInfoByType.do")
	@ResponseBody
	public JsonResult selectResouceInfoByType(@RequestBean Short type){
		JsonResult jsonResult = new JsonResult();
		if(type == null || type < 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			ServiceResult<List<ResourceInfo>> result = resourceInfoService.selectResouceInfoByType(type);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("查询成功");
			if(result.getData() != null){
				jsonResult.addData("homeImgList", result.getData());
				jsonResult.setDataObj(result.getData().size());
			}else{
				jsonResult.setDataObj(0);
			}
		}
		return jsonResult;
	}
	
	/**
	 * 更换图片
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月13日 下午6:07:51
	 *@Version:1.0
	 */
	@RequestMapping("/updateResourceInfo.do")
	@ResponseBody
	public JsonResult updateResourceInfo( @RequestBean ResourceInfoReplacePara resourceInfo){
		JsonResult jsonResult = new JsonResult();
		if(resourceInfo != null){
			if(resourceInfo.getFromId() == null){
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setSuccess(false);
				jsonResult.setMessage("参数错误");
				return jsonResult;
			}
			ResourceInfo resInf = new ResourceInfo();
			resInf.setRemark(resourceInfo.getRemark());
			if(resourceInfo.getTargetId() != null){
				resInf.setId(resourceInfo.getTargetId());
				resInf.setNo(resourceInfo.getFromNo());
				resourceInfoService.updateByPrimaryKeySelective(resInf);
			}
			resInf.setId(resourceInfo.getFromId());
			resInf.setNo(resourceInfo.getTargetNo());
			resourceInfoService.updateByPrimaryKeySelective(resInf);
			AdminUser user = getAdminUser(getRequest());
			logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-首页", "修改了图片信息");
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("恭喜，设置成功");
		}
		return jsonResult;
	}
}
