package com.jialian.o2o.controller.order;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.vo.OrderTrackVO;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.Order.OrderTrackServiceApi;
import com.jialian.api.service.Order.PayRecordServiceApi;
import com.jialian.api.service.Order.SignedOrderServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 * @Description: 这是O2O个人中心里用户装修订单模块
 * @Param:
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2015年12月17日下午5:28:56
 * @Version:1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderTrackServiceApi orderTrackService;
	
	@Resource
	private PayRecordServiceApi payRecordService;
	
	@Resource
	private SignedOrderServiceApi signedOrderService;
	
	@Resource
	private UserServiceApi userService;
	/**
	 * @Description: 根据用户ID获取用户签约订单列表
	 * @Param:@param userId 用户ID
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月17日下午6:00:56
	 * @Version:1.0
	 */
	@RequestMapping("/getSinedOrderByUserId.do")
	@ResponseBody
	public JsonResult getSinedOrderByUserId(Long userId){
		JsonResult result = new JsonResult();
		//判断参数
		if(userId == null || userId <= 0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		//创建查询条件
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("user_id", userId);
		List<SignedOrder> signedOrderList = signedOrderService.selectByWhere(where);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setStateCode(Const.SUCCESS_CODE);
		result.addData("orderList", signedOrderList);
		return result;
	}
	
	/**
	 * @Description:获取用户的签约订单跟踪列表
	 * @Param:@param userId 用户ID
	 * @Param:@param orderId 签约订单ID
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月17日下午5:59:46
	 * @Version:1.0
	 */
	@RequestMapping("/getOrderTrackList.do")
	@ResponseBody
	public JsonResult getOrderTrackList(@RequestBean Long userId, @RequestBean Long orderId ){
		JsonResult result = new JsonResult();
		// 判断参数
		if (userId == null || userId <= 0 || orderId == null || orderId <= 0) {
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("user_id", userId).andEqualTo("order_id", orderId);
		List<OrderTrack> orderTrackList = orderTrackService.selectByWhere(where);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setStateCode(Const.SUCCESS_CODE);
		result.addData("orderTrackList", orderTrackList);
		return result;
	}
	
	/**
	 * @Description:获取用户需要付款的具体信息
	 * @Param:@param id payRecord ID
	 * @Param:@param orderId 签约订单ID号
	 * @Param:@param userId 用户ID号
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月17日下午5:57:53
	 * @Version:1.0
	 */
	@RequestMapping("/getPayOrderDetail.do")
	@ResponseBody
	public JsonResult getPayOrderDetail(@RequestBean Long id, @RequestBean Long orderId, @RequestBean Long userId) {
		JsonResult result = new JsonResult();
		// 判断参数
		if (userId == null || userId <= 0 || orderId == null || orderId <= 0 || id == null || id <= 0) {
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("id", id).andEqualTo("order_id", orderId).andEqualTo("user_id", userId);
		List<PayRecord> payRecord = payRecordService.selectByWhere(where);
		if (payRecord == null || payRecord.size() == 0) {
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_NODATA_CODE);
			result.setMessage("查询失败");
		}else{
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.addData("payRecord", payRecord.get(0));
			result.setMessage("查询成功");
		}
		return result;
	}
	
	/**
	 * @Description:根据ID获取跟踪记录详情
	 * @Param:@param id 跟踪订单ID
	 * @Param:@param type 跟踪订单类型 1为付款详情，2半包清单详情
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月28日下午4:46:19
	 * @Version:1.0
	 */
	@RequestMapping("getTrackOrderDetail.do")
	@ResponseBody
	public JsonResult getTrackOrderDetail(@RequestBean Long id, @RequestBean Short type){
		JsonResult result = new JsonResult();
		//判断参数
		if(id == null || id <= 0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		OrderTrackVO orderTrackDetail = new OrderTrackVO();
		switch(type){
			case 1 : orderTrackDetail = orderTrackService.selectTrackOrderAndPayRecord(id);break;
			case 2 : orderTrackDetail = orderTrackService.selectTrackOrderAndSemDecOrder(id);break;
			default : break;
		}
		result.addData("orderTrackDetail", orderTrackDetail);
		result.setMessage("查询成功");
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		return result;
	}
	
	/**
	 * @Description:
	 * @Param:@param mav
	 * @Param:@param response
	 * @Param:@param request
	 * @Param:@param result
	 * @Param:@param out_trade_no
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月21日下午6:42:28
	 * @Version:1.0
	 */
	@RequestMapping(value = "/paySuccess.htm")
	public void paySuccess(ModelAndView mav,HttpServletResponse response,HttpServletRequest request,String result,String out_trade_no){
		//参数处理
		if(result==null||result.equals("")||out_trade_no==null||out_trade_no.equals("")){//参数为空
			mav.addObject("success", false);
			//mav.setViewName("payError.html");
		}else{
			PayRecord payRecord = new PayRecord();
			if(result.equals("success")){//支付成功
				//向支付记录表中插记录
				Where where = new Where(); 
				Criteria criteria = where.createCriteria();
				criteria.andEqualTo("pay_no", out_trade_no);
				List<PayRecord> payRecordList = payRecordService.selectByWhere(where);
				payRecord = payRecordList.get(0);
				payRecord.setPayStatus((short)1);
				payRecordService.updateByPrimaryKeySelective(payRecord);
				User user = userService.selectByPrimaryKey(payRecord.getUserId());
				OrderTrack orderTrack = new OrderTrack();
				orderTrack.setUserId(user.getId());
				orderTrack.setOrderId(payRecord.getOrderId());
				orderTrack.setTrackTitle("装修订单基础信息更改");
				String trackContent;
				if(user.getSex() == 0){
					trackContent =  user.getNickName() + " 女士，您好！你的订单" + payRecord.getOrderNo() +" "+ payRecord.getPayDescription() +" 已经支付，请查看！";
				}else{
					trackContent =  user.getNickName() + " 先生，您好！你的订单" + payRecord.getOrderNo() +" "+ payRecord.getPayDescription() +" 已经支付，请查看！";
				}
				orderTrack.setTrackContent(trackContent);
				orderTrack.setTrackId(payRecord.getId());
				orderTrack.setTrackType((short)1);
				orderTrackService.insertSelective(orderTrack);
				mav.addObject("payRecord", payRecord);
				mav.addObject("success", true);
			}else{//支付失败
				mav.addObject("success", false);
				//mav.setViewName("payError.html");
			}
			mav.setViewName("/index.html#/userCenter/payOrder/" + payRecord.getId());//在该页面ajax请求findCharge.do获取订单支付状态
		}
		
		try {
			response.sendRedirect("../index.html#/paysuccess");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return mav;
	}
}
