package com.jialian.o2o.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.vo.HomeVo;
import com.jialian.api.domain.vo.PubDocVo;
import com.jialian.api.service.HomeServiceApi;
import com.jialian.api.service.Combo.ComboServiceApi;
import com.jialian.api.service.PubDoc.PubDocServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description: 首页控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月15日 下午2:13:35
 *@Version:1.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Resource
	HomeServiceApi homeServiceApi;

	@Resource
	ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	ComboServiceApi comboServiceApi;
	
	@Resource
	PubDocServiceApi pubDocService;
	/**
	* @Description: 首页信息
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 下午2:16:03
	 */
	@RequestMapping("/homeMes.do")
	@ResponseBody
	public JsonResult homeMes(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<HomeVo> result=homeServiceApi.getHomeMes();
		jsonResult.setData(result.getDataMap());
		jsonResult.setSuccess(result.isOk());
		return jsonResult;
	}
	
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
		List<Object> list = new ArrayList<>();
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
	* @Description: 套餐列表
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月21日 下午4:52:14
	 */
	@RequestMapping("/getComboAll.do")
	@ResponseBody
	public JsonResult getComboAll(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<Combo>> result=comboServiceApi.getComboAll();
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	/**
	 * @Description:
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月29日下午1:46:05
	 * @Version:1.0
	 */
	@RequestMapping("/getPublicDoc.do")
	@ResponseBody
	public JsonResult getPublicDoc(Long id){
		JsonResult result=new JsonResult();
		ServiceResult<PubDocVo> serviceResult = pubDocService.getPubDocDetailed(id);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.addData("doc", serviceResult.getData());
		result.setMessage("查询成功");
		return result;
	}
}
