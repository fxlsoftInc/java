/**
 * 
 */
package com.jialian.shop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.service.Material.MaterialAttributeServiceApi;
import com.jialian.api.service.Material.MaterialServiceApi;
import com.jialian.api.service.Shop.ShopCartServiceApi;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 * @Description: 商城订单
 * @Author: zhls 联系方式：zhls1992@qq.com
 * @Since:2016年1月9日 下午6:40:00
 * @Version:1.0
 */
@Controller
public class ShopOrderController {

	@Resource
	private ShopOrderServiceApi shopOrderServiceApi;
	@Resource
	private IdWorkerServiceApi idWorkerServiceApi;
	@Resource
	private MaterialServiceApi materialServiceApi;
	@Resource
	private MaterialAttributeServiceApi MaterialAttributeServiceApi;
	@Resource
	private ShopCartServiceApi shopCartServiceApi;

	/**
	  * @Title: addShopOrder
	  * @Description: 提交订单
	  * @param userId 用户id
	  * @param totalAmt 合计金额 =postage+ orderAmt-discountAmt
	  * @param postage 邮费
	  * @param discountAmt 折扣金额
	  * @param orderAmt 订单金额
	  * @param prepayAmt 预付金额
	  * @param payedAmt 已付金额
	  * @param useraddr 用户地址
	  * @param addrid 地址id
	  * @param ispinkageAmt 是否包邮
	  * @param shopCartIds ptype=1购物车idlist，ptype=2 shopCartIds 商品id，属性id,数量
	  * @param ptype 1是购物车结算2是直接结算
	  * @param linktel 联系电话
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 下午2:51:42
	 */
	@RequestMapping("/addShopOrder.do")
	@ResponseBody
	public JsonResult addShopOrder(@RequestBean Long userId, @RequestBean Double totalAmt,@RequestBean Integer ptype,
			@RequestBean Double postage, @RequestBean Double discountAmt, @RequestBean Double orderAmt,
			@RequestBean Double prepayAmt, @RequestBean Double payedAmt, @RequestBean String useraddr, @RequestBean Long addrid,
			@RequestBean Short ispinkageAmt, @RequestBean String shopCartIds, @RequestBean String linktel, @RequestBean String linkuser, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		if(discountAmt==null||discountAmt<0)discountAmt=0.0;
		if(totalAmt==null||totalAmt<0)totalAmt=0.0;
		if(postage==null||postage<0)postage=0.0;
		if(payedAmt==null||payedAmt<0)payedAmt=0.0;
		if(orderAmt==null||orderAmt<0)payedAmt=0.0;
		try {
			//参数处理
			if(userId==null||userId<=0||addrid==null||addrid<=0||shopCartIds==null||shopCartIds.equals("")||ispinkageAmt==null){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("参数为空");
				return jsonResult;
			}
			// 订单编号
			String orderNo = "SORD" + idWorkerServiceApi.nextId();
			ServiceResult<Object> result = shopOrderServiceApi.addShopOrder(
					userId, totalAmt, postage, discountAmt, orderAmt,
					prepayAmt, payedAmt, useraddr, addrid, ispinkageAmt,
					shopCartIds, orderNo,ptype,linktel,linkuser);

			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			jsonResult.setDataObj(result.getData());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}

		return jsonResult;
	}
	/**
	  * @Title: getMyShopOrders
	  * @Description: 获取个人商城订单
	  * @param userId 用户id
	  * @param orderno 订单单号
	  * @param pageSize 页面记录数
	  * @param pageIndex 页面号
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 上午11:02:55
	 */
	@RequestMapping("/getMyShopOrders.do")
	@ResponseBody
	public JsonResult getMyShopOrders(@RequestBean Long userId,@RequestBean String orderno, @RequestBean Integer pageSize, @RequestBean Integer pageIndex, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try{
			ServiceResult<Object> result = shopOrderServiceApi.selectByUserId_Orderno(userId,orderno,pageIndex,pageSize);
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			jsonResult.setDataObj(result.getDataMap());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}

	return jsonResult;
	}
	/**
	  * @Title: getShopOrderDetail
	  * @Description: 订单详情
	  * @param orderId 订单id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 下午4:25:56
	 */
	@RequestMapping("/getShopOrderDetail.do")
	@ResponseBody
	public JsonResult getShopOrderDetail(@RequestBean Long orderId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try{
			ShopOrder result = shopOrderServiceApi.selectByPrimaryKey(orderId);
			if(result!=null){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setComment("操作成功");
				jsonResult.setDataObj(result);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setComment("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: deleteOrder
	  * @Description: 删除订单
	  * @param orderId
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 下午11:08:07
	 */
	@RequestMapping("/deleteShopOrder.do")
	@ResponseBody
	public JsonResult deleteShopOrder(@RequestBean Long orderId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try{
			ShopOrder result = shopOrderServiceApi.selectByPrimaryKey(orderId);
			if(result!=null){
				result.setStatus((short) 0);
				int flag = shopOrderServiceApi.updateByPrimaryKeySelective(result);
				if(flag == 1){
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setComment("操作成功");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setComment("操作失败");
				}
				
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setComment("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: cancelShopOrder
	  * @Description: 取消订单
	  * @param orderId
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 下午11:08:48
	 */
	@RequestMapping("/cancelShopOrder.do")
	@ResponseBody
	public JsonResult cancelShopOrder(@RequestBean Long orderId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try{
			ShopOrder result = shopOrderServiceApi.selectByPrimaryKey(orderId);
			if(result!=null){
				result.setOrderStatus((short) 4);
				int flag = shopOrderServiceApi.updateByPrimaryKeySelective(result);
				if(flag == 1){
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setComment("操纵成功");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setComment("操作失败");
				}
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setComment("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
}
