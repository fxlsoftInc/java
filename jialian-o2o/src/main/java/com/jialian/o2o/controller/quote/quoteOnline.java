package com.jialian.o2o.controller.quote;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.QuoteOnlineQuery;
import com.jialian.api.domain.vo.QuoteOnlineVO;
import com.jialian.api.service.Quote.QuoteServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/quote")
public class quoteOnline {

	@Resource
	private QuoteServiceApi quoteService;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@RequestMapping("/quoteOnline.do")
	@ResponseBody
	public JsonResult quoteOnlineQuery(@RequestBean QuoteOnlineQuery qQuote){
		JsonResult result = new JsonResult();
		//参数判定
		if(qQuote.getArea() <= 0){
			result.setSuccess(false);
			result.setMessage("建筑面积不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		if(qQuote.getWo() <= 0 && qQuote.getChu() <= 0 && qQuote.getTing() <= 0 && qQuote.getWei() <= 0 && qQuote.getYang() <= 0){
			result.setSuccess(false);
			result.setMessage("间数不能全部为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
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
}
