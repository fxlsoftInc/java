package com.jialian.platform.controller.house;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.House.HouseStructureInfoServiceApi;

/**
 *@Description: 房屋结构信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月21日 下午6:21:03
 *@Version:1.0
 */
@Controller
@RequestMapping("/houseStructureInfo")
public class HouseStructureInfoController {
	
	@Resource
	HouseStructureInfoServiceApi houseStructureInfoService;

	/**
	* @Description: 房屋结构信息修改
	* @param @param info
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午6:24:00
	 */
	@RequestMapping("/updateHouseStructureInfo.do")
	@ResponseBody
	public JsonResult updateHouseStructureInfo(HouseStructureInfo info){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=houseStructureInfoService.updateHouseStructureInfo(info);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
	/**
	* @Description: 房屋结构信息添加
	* @param @param info
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午5:23:20
	 */
	@RequestMapping("/addHouseStructureInfo.do")
	@ResponseBody
	public JsonResult addHouseStructureInfo(HouseStructureInfo info){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=houseStructureInfoService.addHouseStructureInfo(info);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
	/**
	 * @Description:获取房间分类列表
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月7日下午6:05:13
	 * @Version:1.0
	 */
	@RequestMapping("/getHouseStructureInfoList.do")
	@ResponseBody
	public JsonResult getHouseStructureInfoList(){
		JsonResult result = new JsonResult();
		Where where = new Where();
		List<HouseStructureInfo> houseStrInfoList= houseStructureInfoService.selectByWhere(where);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("查询成功");
		result.addData("houseStrInfoList", houseStrInfoList);
		return result;
	}
}
