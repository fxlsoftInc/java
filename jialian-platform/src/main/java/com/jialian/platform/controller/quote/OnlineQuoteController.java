package com.jialian.platform.controller.quote;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.LaborCost;
import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.SemiDecorationOrderInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.QuotePara;
import com.jialian.api.domain.query.QuoteOnlineQuery;
import com.jialian.api.domain.vo.QuoteOnlineVO;
import com.jialian.api.service.House.HouseStructureInfoServiceApi;
import com.jialian.api.service.Item.ItemServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.api.service.Material.MaterialServiceApi;
import com.jialian.api.service.Quote.QuoteServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/quote")
public class OnlineQuoteController {

	@Resource
	private QuoteServiceApi quoteService;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private HouseStructureInfoServiceApi houseStrInfoService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	
	@Resource
	private ItemServiceApi itemService;
	
	@Resource
	private MaterialServiceApi materialService;
	
	@Resource
	private LogServiceApi logService;
	
	@RequestMapping("/quoteOnline.do")
	@ResponseBody
	public JsonResult quoteOnlineQuery(Short type){
		JsonResult result = new JsonResult();
		//参数判定
		if(type != 1){
			type = 0;
		}
		QuoteOnlineQuery qQuote = new QuoteOnlineQuery();
		qQuote.setType(type);
		ServiceResult<QuoteOnlineVO> sResult = quoteService.quoteOnline(qQuote);
		if(sResult.isOk()){
			result.setSuccess(true);
			result.addData("quote", sResult.getData());
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("查询成功");
			return result;
		}else{
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE);
			result.setMessage(sResult.getComment());
			return  result;
		}
		
	}
	
	/**
	 * @Description:获取广告图片
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日下午1:06:53
	 * @Version:1.0
	 */
	@RequestMapping("/getADImage.do")
	@ResponseBody
	public JsonResult getADImage(){
		JsonResult result = new JsonResult();
		Where where = new Where();
		ResourceInfo resourceInfo = new ResourceInfo();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("no", "QUOTE0001");
		List<ResourceInfo> resourceInfoList = resourceInfoService.selectByWhere(where);
		if(resourceInfoList == null || resourceInfoList.size() == 0){
			resourceInfo.setNo("QUOTE0001");
			resourceInfo.setObjectId(0l);
			resourceInfo.setObjectType((short)10);
		}else{
			resourceInfo = resourceInfoList.get(0);
		}
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("SUCCESS");
		result.addData("resourceInfo", resourceInfo);
		return result;
	}
	
	/**
	 * @Description:获取房屋信息
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日下午1:38:22
	 * @Version:1.0
	 */
	@RequestMapping("/getHouseStrInfo.do")
	@ResponseBody
	public JsonResult getHouseStrInfo(){
		JsonResult result = new JsonResult();
		Where where = new Where();
		List<HouseStructureInfo> houseStructureInfo = houseStrInfoService.selectByWhere(where);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("查询成功");
		result.addData("hStrInfo", houseStructureInfo);
		return result;
	}
	
	/**
	 * @Description:修改房屋信息
	 * @Param:@param hStrInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日下午1:44:37
	 * @Version:1.0
	 */
	@RequestMapping("/updateHouseStrInfo.do")
	@ResponseBody
	public JsonResult updateHouseStrInfo(@RequestBean ArrayList<HouseStructureInfo> hStrInfoList){
		JsonResult result = new JsonResult();
		for(int i = hStrInfoList.size() - 1; i >= 0; i--){
			if(hStrInfoList.get(i).getId() == null){
				continue;
			}
			houseStrInfoService.updateByPrimaryKeySelective(hStrInfoList.get(i));
		}
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("修改成功");
		return result;
	}
	
	/**
	 * @Description:删除详情
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月14日下午3:45:01
	 * @Version:1.0
	 */
	@RequestMapping("/deleteSemiOrderInfoById.do")
	@ResponseBody
	public JsonResult deleteSemiOrderInfoById(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <= 0){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}else{
			SemiDecorationOrderInfo semOrderInfo = new SemiDecorationOrderInfo();
			semOrderInfo.setId(id);
			semOrderInfo.setStatus((short)0);
			quoteService.updateByPrimaryKeySelective(semOrderInfo);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("删除成功");
			return result;
		}
	}
	
	/**
	 * @Description:修改半包清单详情
	 * @Param:@param quote
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月13日下午6:36:00
	 * @Version:1.0
	 */
	@RequestMapping("/updateSemiOrderInfo.do")
	@ResponseBody
	public JsonResult updateSemiOrderInfo(@RequestBean QuotePara quote){
		JsonResult result = new JsonResult();
		if(quote == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		//先项目
		Item item = new Item();
		item.setItemName(quote.getItemName());
		item.setItemCraft(quote.getItemCraft());
		item.setItemType(quote.getItemType());
		item.setHouseStructureCategoryId(quote.getHouseStrCatId());
		if(quote.getItemId() == null || quote.getItemId() == 0){
			item.setItemNo("ITEM" + idWorker.nextId());
			item.setStatus((short)1);
			item.setRemark("在线报价");
			item = itemService.addItem(item);
		}else{
			item.setId(quote.getItemId());
			itemService.updateByPrimaryKeySelective(item);
		}
		//主材
		Material mainMaterial = new Material();
		mainMaterial.setMaterialPrice(quote.getMainMaterialPrice());
		mainMaterial.setMaterialUnit(quote.getMainMaterialUnit());
		if(quote.getMainMaterialId() == null || quote.getMainMaterialId() == 0){
			mainMaterial.setMaterialNo("MATERIAL" + idWorker.nextId());
			mainMaterial.setMaterialType((short)0);
			mainMaterial = materialService.insertSelective(mainMaterial);
		}else{
			mainMaterial.setId(quote.getMainMaterialId());
			materialService.updateByPrimaryKeySelective(mainMaterial);
		}
		//辅材
		Material auxiMaterial = new Material();
		auxiMaterial.setMaterialPrice(quote.getAuxiMaterialPrice());
		auxiMaterial.setMaterialUnit(quote.getAuxiMaterialUnit());
		if(quote.getAuxiMaterialId() == null || quote.getAuxiMaterialId() == 0){
			auxiMaterial.setMaterialNo("MATERIAL" + idWorker.nextId());
			mainMaterial.setMaterialType((short)1);
			auxiMaterial = materialService.insertSelective(auxiMaterial);
		}else{
			auxiMaterial.setId(quote.getAuxiMaterialId());
			materialService.updateByPrimaryKeySelective(auxiMaterial);
		}
		//人工费
		LaborCost laborCost = new LaborCost();
		laborCost.setLaborCost(quote.getLaborCost());
		if(quote.getLaborId() == null || quote.getLaborId() == 0){
			laborCost.setRemark("在线报价");
			laborCost = quoteService.insertSelective(laborCost);
		}else{
			laborCost.setId(quote.getLaborId());
			quoteService.updateByPrimaryKeySelective(laborCost);
		}
		//详情表
		SemiDecorationOrderInfo semOrderInfo = new SemiDecorationOrderInfo();
		if(quote.getType() != 1){
			quote.setType((short)2);
		}
		semOrderInfo.setSemiDecOrderId(quote.getType().longValue());
		semOrderInfo.setItemId(item.getId());
		semOrderInfo.setItemName(item.getItemName());
		semOrderInfo.setItemNo(item.getItemNo());
		semOrderInfo.setItemMainMaterialId(mainMaterial.getId());
		semOrderInfo.setMainMaterialPrice(mainMaterial.getMaterialPrice());
		semOrderInfo.setItemAuxiliaryMaterialId(auxiMaterial.getId());
		semOrderInfo.setAuxiliaryMaterialPrice(auxiMaterial.getMaterialPrice());
		semOrderInfo.setLaborCost(laborCost.getLaborCost());
		semOrderInfo.setLaborCostId(laborCost.getId());
		semOrderInfo.setItemCraft(item.getItemCraft());
		semOrderInfo.setItemType(item.getItemType());
		if(quote.getId() == null || quote.getId() == 0){
			semOrderInfo.setRemark("在线报价模板");
			semOrderInfo = quoteService.insertSelective(semOrderInfo);
		}else{
			semOrderInfo.setId(quote.getId());
			quoteService.updateByPrimaryKeySelective(semOrderInfo);
		}
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("修改成功");
		return result;
	}
}
