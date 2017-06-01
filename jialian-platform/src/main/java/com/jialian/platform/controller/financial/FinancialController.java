package com.jialian.platform.controller.financial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.PayRecordPara;
import com.jialian.api.service.Order.PayRecordServiceApi;
import com.jialian.api.service.Order.SignedOrderServiceApi;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.DateTimeUtils;
import com.jialian.common.util.ObjectExcelView;

@Controller
@RequestMapping("/financial")
public class FinancialController {

	@Resource
	private PayRecordServiceApi payRecordService;
	
	@Resource
	private ShopOrderServiceApi shopOrderReaderService;
	
	@Resource
	private SignedOrderServiceApi signedOrderService;
	/**
	 * @Description:
	 * @Param:@param para
	 * @Param:@param startIndex
	 * @Param:@param pageSize
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月27日下午6:15:18
	 * @Version:1.0
	 */
	@RequestMapping("/payOrderList.do")
	@ResponseBody
	public JsonResult getPayOrderListByWhere(@RequestBean PayRecordPara para,
			@RequestBean int startIndex,@RequestBean int pageSize){
		JsonResult result = new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		if(para != null ){
			Criteria criteria = where.createCriteria();
			if(para.getOrderNo() != null){
				criteria.andLike("order_no", "%"+ para.getOrderNo() +"%");
			}
			if(para.getPayNo() != null){
				criteria.andLike("pay_no", "%"+ para.getPayNo() +"%");
			}
			if(para.getStartTime() != null){
				criteria.andGreaterThanOrEqualTo("pay_time", para.getStartTime());
			}
			if(para.getEndTime() != null){
				criteria.andLessThanOrEqualTo("pay_time", para.getEndTime());
			}
			if(para.getOrderType() != null){
				criteria.andEqualTo("order_type", para.getOrderType());
			}
			if(para.getPayType() != null){
				criteria.andEqualTo("pay_type", para.getPayType());
			}
		}
		List<PayRecord> payRecordList = payRecordService.selectByWhere(where);
		int totalItems = payRecordService.countByWhere(where);
		result.setSuccess(true);
		result.setMessage("查询成功！");
		result.addData("payRecordList", payRecordList);
		result.addData("totalItems", totalItems);
		return result;
	}
	
	/**
	 * @Description:统计
	 * @Param:@param para
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月27日下午6:50:02
	 * @Version:1.0
	 */
	@RequestMapping("/getTotalStatistics.do")
	@ResponseBody
	public JsonResult getTotalStatistics(@RequestBean PayRecordPara para){
		JsonResult result = new JsonResult();
		Where where = new Where();
		if(para != null ){
			Criteria criteria = where.createCriteria();
			if(para.getOrderNo() != null){
				criteria.andLike("order_no", "%"+ para.getOrderNo() +"%");
			}
			if(para.getPayNo() != null){
				criteria.andLike("pay_no", "%"+ para.getPayNo() +"%");
			}
			if(para.getStartTime() != null){
				criteria.andGreaterThanOrEqualTo("pay_time", para.getStartTime());
			}
			if(para.getEndTime() != null){
				criteria.andLessThanOrEqualTo("pay_time", para.getEndTime());
			}
			if(para.getOrderType() != null){
				criteria.andEqualTo("order_type", para.getOrderType());
			}
			if(para.getPayType() != null){
				criteria.andEqualTo("pay_type", para.getPayType());
			}
		}
		Double total = payRecordService.sumPayAmtByWhere(where);
		Integer o2oItemSum = signedOrderService.countByWhere(null);
		Integer shopItemSum = shopOrderReaderService.countByWhere(null);
		result.setSuccess(true);
		result.setMessage("查询成功！");
		result.addData("total", total);
		result.addData("o2oItemSum", o2oItemSum);
		result.addData("shopItemSum", shopItemSum);
		return result;
	}
	
	
	@RequestMapping("/exportFinancialExcel.do")
	public ModelAndView exportFinancialExcel(Long[] idList){
		Where where = new Where();
		List<Object> list = new ArrayList<Object>();
		if(idList == null){
			idList = new Long[]{0l};
		}
		for(int i = 0; i < idList.length; i++){
			list.add(idList[i]);
		}
		Criteria criteria = where.createCriteria();
		criteria.andIn("id", list);
		List<PayRecord> payRecordList = payRecordService.selectByWhere(where);
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		
		titles.add("序号");
		titles.add("财务流水编号");
		titles.add("支付时间");
		titles.add("金额");
		titles.add("所属业务");
		titles.add("订单编号");
		titles.add("支付方式");
		titles.add("第三方交易凭证");
		titles.add("支付人");
		
		dataMap.put("titles", titles);
		List<JSONObject> contentList = new ArrayList<JSONObject>();
		for(int i = 0; i < payRecordList.size(); i++){
			JSONObject content = new JSONObject();
			content.put("var1", i+1);
			content.put("var2", payRecordList.get(i).getPayNo());
			content.put("var3", DateTimeUtils.format(payRecordList.get(i).getPayTime()));
			content.put("var4", payRecordList.get(i).getPayableAmt());
			content.put("var5", payRecordList.get(i).getOrderType() == 0?"装修专区":"商城专区");
			content.put("var6", payRecordList.get(i).getOrderNo());
			String payType = "未知";
			if(payRecordList.get(i).getPayType() == null){
				payRecordList.get(i).setPayType((short)-1);
			}
			switch(payRecordList.get(i).getPayType()){
				case 0:payType = "微信";
				break;
				case 1:payType = "支付宝";
				break;
				default:payType = "未知";
				break;
			}
			content.put("var7", payType);
			content.put("var8", payRecordList.get(i).getObjid());
			content.put("var9", payRecordList.get(i).getUser().getUserName());
			contentList.add(content);
		}
		dataMap.put("varList", contentList);
		ObjectExcelView erv = new ObjectExcelView();
		ModelAndView vm = new ModelAndView(erv,dataMap);
		return vm;
	}
}
