/**
 * 
 */
package com.jialian.o2o.controller.userCenter;

/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月11日 下午3:55:06  */

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Order.PayRecordServiceApi;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.AmountUtils;
import com.jialian.common.util.SoleIpUtil;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;

/**
 * @Description: 支付
 * @Author: zhls 联系方式：zhls1992@qq.com
 * @Since:2016年1月11日 下午3:55:06
 * @Version:1.0
 */
@Controller
@RequestMapping("/pay")
public class ShopPayController {

	@Resource
	private ShopOrderServiceApi shopOrderServiceApi;
	@Resource
	private IdWorkerServiceApi idWorkerServiceApi;
	@Resource
	private PayRecordServiceApi payRecordServiceApi;
	
	/**
	 * @Title: topayServlet
	 * @Description: 去支付
	 * @param amount
	 *            订单总金额, 单位为对应币种的最小货币单位，例如：人民币为分（如订单总金额为 1 元，此处请填 100）。
	 * @param currency
	 *            货币类型
	 * @param subject
	 *            商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节
	 * @param body商品的描述信息
	 *            ，该参数最长为 128 个 Unicode 字符
	 * @param order_no
	 *            商户订单号
	 * @param userId
	 *            用户id
	 * @param prodAttrIds
	 *            商品属性ids
	 * @param channel
	 *            支付使用的第三方支付渠道，alipay:支付宝手机支付 alipay_wap:支付宝手机网页支付
	 *            alipay_qr:支付宝扫码支付 alipay_pc_direct:支付宝 PC 网页支付 apple_pay:Apple
	 *            Pay bfb:百度钱包移动快捷支付 bfb_wap:百度钱包手机网页支付 upacp:银联全渠道支付（2015 年 1 月
	 *            1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
	 *            upacp_wap:银联全渠道手机网页支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++
	 *            或者相关的收单行联系） upacp_pc:银联 PC 网页支付 cp_b2b:银联企业网银支付 wx:微信支付
	 *            wx_pub:微信公众账号支付 wx_pub_qr:微信公众账号扫码支付 yeepay_wap:易宝手机网页支付
	 *            jdpay_wap:京东手机网页支付 cnp_u:应用内快捷支付（银联） cnp_f:应用内快捷支付（外卡）
	 * extra
	 *            alipay: (支付宝 手机支付)。 参数 extern_token[string] 开放平台返回的包含账户信息的
	 *            token（授权令牌，商户在一定时间内对支付宝某些服务的访问权限）。通过授权登录后获取的
	 *            alipay_open_id，作为该参数的 value，登录授权账户即会为支付账户，32 位字符串，optional； 参数
	 *            rn_check[string] 是否发起实名校验，optional； 支付完成将额外返回付款用户的支付宝账号
	 *            buyer_account 。 alipay_wap: (支付宝 手机网页支付)。 参数
	 *            success_url[string] 为支付成功的回调地址，required； 参数 cancel_url[string]
	 *            为支付取消的回调地址，optional； 支付完成将额外返回付款用户的支付宝账号 buyer_account 。
	 *            alipay_pc_direct: (支付宝 PC 网页支付)。 参数 success_url[string]
	 *            为支付成功的回调地址，required； wx: (微信支付)。 支付完成将额外返回付款用户的 open_id
	 *            和付款银行类型 bank_type。 wx_pub: (微信 公众账号支付)。 参数 open_id[string]
	 *            为用户在商户 appid 下的唯一标识，required； 支付完成将额外返回付款银行类型 bank_type 。
	 *            upacp_wap: (银联全渠道 手机网页支付)。 参数 result_url[string]
	 *            为支付完成的回调地址，required； upacp_pc: (银联 PC 网页支付)。 参数
	 *            result_url[string] 为支付完成的回调地址，required； bfb_wap: (百度钱包
	 *            手机网页支付)。 参数 result_url[string] 为支付完成的回调地址，required； 参数
	 *            bfb_login[boolean] 为是否需要登录百度钱包来进行支付，required； apple_pay: 参数
	 *            payment_token[string] 为支付所需的支付令牌，从 client 获得，required；
	 *            wx_pub_qr: (微信公众账号 扫码支付)。 参数 product_id[string] 为商品 ID，1-32
	 *            位字符串。此 id 为二维码中包含的商品 ID，商户自行维护，required； 支付完成将额外返回付款用户的
	 *            open_id 和付款银行类型 bank_type 。 yeepay_wap: (易宝 手机网页支付)。 参数
	 *            product_category[string] 为商品类别码，详见商品类型码表，required； 参数
	 *            identity_id[string] 为用户标识,商户生成的用户账号唯一标识，最长 50 位字符串，required；
	 *            参数 identity_type[int] 为用户标识类型，详见用户标识类型码表，required； 参数
	 *            terminal_type[int] 为终端类型，对应取值 0:IMEI, 1:MAC, 2:UUID,
	 *            3:other，required； 参数 terminal_id[string] 为终端 ID，required； 参数
	 *            user_ua[string] 为用户使用的移动终端的 UserAgent 信息，required； 参数
	 *            result_url[string] 为前台通知地址，required。 jdpay_wap: (京东 手机网页支付)。
	 *            参数 success_url[string] 为支付成功页面跳转路径，required； 参数
	 *            fail_url[string] 为支付失败页面跳转路径，required； 参数 token[string]
	 *            为用户交易令牌，用于识别用户信息，支付成功后会调用 success_url 返回给商户。商户可以记录这个 token
	 *            值，当用户再次支付的时候传入该 token，用户无需再次输入银行卡信息，直接输入短信验证码进行支付。32
	 *            位字符串，optional； cnp_u: 应用内快捷支付（银联） 参数 source[string] 为 Token 对象
	 *            id，required； 参数 sms_code[id][string] 为 SMS Code 对象
	 *            id，当为银联卡时必填，required； 参数 sms_code[code][string]
	 *            为短信验证码，当为银联卡时必填。，required； cnp_f: 应用内快捷支付（外卡） 参数
	 *            source[string] Token 对象 id，required；
	 * @return
	 * @return 返回类型 JsonResult
	 * @throws
	 * @Author: zhls 联系方式：zhls1992@qq.com
	 * @Since: 2016年1月11日 下午4:30:09
	 */
	@RequestMapping("/topayServlet.do")
	@ResponseBody
	public JsonResult topayServlet( Double amount,  String currency, Long userId, String prodAttrIds,
			 String subject,  String body,  String order_no,  String channel,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		 if(amount==null||amount<0)amount=0.0;
		 if(currency==null||currency.equals(""))currency="cny";
		try {
			// 参数处理
			 if(userId==null||userId<=0||subject==null||subject.equals("")||body==null||body.equals("")||order_no==null||order_no.equals("")||channel==null||channel.equals("")){
				 jsonResult.setSuccess(false);
				 jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				 jsonResult.setMessage("参数为空");
				 return jsonResult;
			 }
			// 获取ip
			String client_ip = SoleIpUtil.getIpAddr(request);
			Pingpp.apiKey = Const.apiKey;
			Charge charge = null;
			Map<String, Object> chargeMap = new HashMap<String, Object>();
			chargeMap.put("amount", AmountUtils.changeY2F(amount+""));
			chargeMap.put("currency", currency);
			chargeMap.put("subject", subject);
			chargeMap.put("body", body);
			chargeMap.put("order_no", order_no);
			chargeMap.put("channel", channel);
			chargeMap.put("client_ip", client_ip);
			chargeMap.put("metadata[order_no]", order_no);
			chargeMap.put("metadata[userId]", userId);
			chargeMap.put("metadata[prodAttrIds]", prodAttrIds);
			JSONObject extra = new JSONObject();
			if(channel.equals("alipay_pc_direct")){
				extra.put("success_url", Const.success_url);
				chargeMap.put("extra", extra);
			}
			Map<String, String> app = new HashMap<String, String>();
			app.put("id", Const.appId);// 支付使用的 app 对象的 id
			chargeMap.put("app", app);
			// 发起交易请求
			charge = Charge.create(chargeMap);
			System.out.println(charge);
			if(charge!=null){
				//对数据库处理
				Date completeTime = new Date();
				ShopOrder shopOrder = shopOrderServiceApi.selectByOrderno(order_no);
				shopOrder.setIsvalid((short)0);
				shopOrder.setChargeId(charge.getId());
				shopOrder.setUpdateTime(completeTime);
				shopOrder.setPayedAmt(amount);
				shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
				//支付记录表
				//向支付记录表中插记录
				String payno = "PAY"+idWorkerServiceApi.nextId();
				PayRecord pr = new PayRecord();
				pr.setPayNo(payno);
				pr.setAccessUserAccountNumber("");//收款人账号
				pr.setAccessUserId((long) 0);//收款人ID，默认为0
				pr.setActualAmt(shopOrder.getPayedAmt());//实际支付金额
				pr.setCreateTime(completeTime);
				pr.setDiscountAmt(0.0);
				pr.setOrderId(shopOrder.getId());
				pr.setOrderType((short)1);//付款订单类型，默认为0，0为O2O订单，1为商城订单
				pr.setPayableAmt(shopOrder.getOrderAmt());
				pr.setPayDescription("商城购物");
				pr.setPayStatus((short)0);//付款状态，0为未付款，1为已付款，2为付款异常
				pr.setPayTime(null);
				pr.setPayType((short)1);//付款类型，1为支付宝，2为微信
				pr.setUpdateTime(completeTime);
				pr.setUserAccountNumber("");//付款人账号，暂时为空
				pr.setUserId(shopOrder.getUserId());
				pr.setRecordType((short) 1);//记录类型1是支付2是退款
				pr.setObjid(charge.getId());
				payRecordServiceApi.insertSelective(pr);
				
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setComment("成功");
				jsonResult.setDataObj(charge);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
			}
			
		} catch (PingppException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}

		return jsonResult;
	}
	
	/**
	 * @Description:O2O付款
	 * @Param:@param userId
	 * @Param:@param payId
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月21日下午6:12:43
	 * @Version:1.0
	 */
	@RequestMapping("/toPayInO2O.do")
	@ResponseBody
	public JsonResult toPayInO2O(String channel, Long userId, Long payId, HttpServletRequest request){
		JsonResult result = new JsonResult();
		if(userId == null || payId == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数为空");
			return result;
		}
		// 获取ip
		PayRecord payRecord = payRecordServiceApi.selectByPrimaryKey(payId);
		if(payRecord == null ){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("不存在该支付订单");
			return result;
		}
		if(payRecord.getPayStatus() == 1){
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("该订单已支付");
			return result;
		}
		String client_ip = SoleIpUtil.getIpAddr(request);
		Pingpp.apiKey = Const.apiKey; 
		Charge charge = null;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", AmountUtils.changeY2F(payRecord.getPayableAmt() + ""));
		chargeMap.put("currency", "cny");
		chargeMap.put("subject", payRecord.getOrderNo() + "装修订单支付");
		chargeMap.put("body", payRecord.getPayDescription());
		chargeMap.put("order_no", payRecord.getPayNo());
		chargeMap.put("channel", channel);
		chargeMap.put("client_ip", client_ip);
		chargeMap.put("metadata[order_no]", payRecord.getOrderNo());
		chargeMap.put("metadata[userId]", userId);
		chargeMap.put("metadata[payNo]", payRecord.getPayNo());
		JSONObject extra = new JSONObject();
		if(channel.equals("alipay_pc_direct")){
			extra.put("success_url", Const.O2O_success_url);
			chargeMap.put("extra", extra);
		}
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", Const.appId);// 支付使用的 app 对象的 id
		chargeMap.put("app", app);
		// 发起交易请求
		try{
			charge = Charge.create(chargeMap);
			if(charge != null){
				//对数据库处理
				payRecord.setObjid(charge.getId());
				payRecordServiceApi.updateByPrimaryKeySelective(payRecord);
				result.setSuccess(true);
				result.setStateCode(Const.SUCCESS_CODE);
				result.setComment("成功");
				result.setDataObj(charge);
			}
		} catch (PingppException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_UNKNOWN_CODE);
			result.setMessage("支付异常");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_UNKNOWN_CODE);
			result.setMessage("服务异常");
			return result;
		}
		
		System.out.println(charge);
		return result;
	}
	/**
	  * @Title: paySuccess
	  * @Description: 支付成功
	  * @param mav
	  * @param request
	  * @param result 支付结果
	  * @param out_trade_no 订单单号
	  * @return  
	  * @return 返回类型  ModelAndView   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 下午3:45:01
	 */
	@RequestMapping(value = "/paySuccess.htm")
	public void paySuccess(ModelAndView mav,HttpServletResponse response,HttpServletRequest request,String result,String out_trade_no){
		//参数处理
		if(result==null||result.equals("")||out_trade_no==null||out_trade_no.equals("")){//参数为空
			mav.addObject("success", false);
			//mav.setViewName("payError.html");
		}else{
			if(result.equals("success")){//支付成功
				Date completeTime = new Date();
				ShopOrder shopOrder = shopOrderServiceApi.selectByOrderno(out_trade_no);
				shopOrder.setOrderStatus((short)1);
				shopOrder.setCompleteTime(completeTime);
				shopOrder.setUpdateTime(completeTime);
				shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
				//向支付记录表中插记录
				Where where = new Where();
				Criteria criteria = where.createCriteria();
				criteria.andEqualTo("order_id", shopOrder.getId());
				criteria.andEqualTo("order_type", 1);
				criteria.andEqualTo("record_type", 1);
				
				List<PayRecord> prllist = payRecordServiceApi.selectByWhere(where);
				if(prllist!=null&&prllist.size()>0){
					PayRecord pr = prllist.get(0);
					pr.setPayStatus((short)1);//付款状态，0为未付款，1为已付款，2为付款异常
					pr.setUpdateTime(completeTime);
					pr.setPayTime(completeTime);
					payRecordServiceApi.updateByPrimaryKeySelective(pr);
				}
				
				mav.addObject("success", true);
				
			}else{//支付失败
				mav.addObject("success", false);
				//mav.setViewName("payError.html");
			}
		}
		mav.setViewName("/index.html#/paysuccess");//在该页面ajax请求findCharge.do获取订单支付状态
		try {
			response.sendRedirect("../index.html#/paysuccess");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return mav;
	}
	/**
	  * @Title: findCharge
	  * @Description: 查询 Charge 对象
	  * @param chargeId 通过charge对象的id查询
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 下午4:35:25
	 */
	@RequestMapping("/findCharge.do")
	@ResponseBody
	public JsonResult findCharge( @RequestBean String chargeId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		try {
			// 参数处理
			 if(chargeId==null||chargeId.equals("")){
				 jsonResult.setSuccess(false);
				 jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				 jsonResult.setMessage("参数为空");
				 return jsonResult;
			 }
			Pingpp.apiKey = Const.apiKey;
			Charge charge = Charge.retrieve(chargeId);
			Date updateTime = new Date();
			System.out.println(charge);
			if(charge!=null){//查询到结果
				ShopOrder shopOrder = shopOrderServiceApi.selectByChargeId(chargeId);
				if(shopOrder!=null){//数据库有记录
					Where where = new Where();
					Criteria criteria = where.createCriteria();
					criteria.andEqualTo("order_id", shopOrder.getId());
					criteria.andEqualTo("order_type", 1);
					criteria.andEqualTo("record_type", 1);
					criteria.andEqualTo("objid", chargeId);
					
					List<PayRecord> prllist = payRecordServiceApi.selectByWhere(where);
					if(charge.getPaid()){//支付成功
						shopOrder.setOrderStatus((short) 1);
						shopOrder.setUpdateTime(updateTime);
						shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
						//支付记录
						if(prllist!=null&&prllist.size()>0){
							PayRecord pr = prllist.get(0);
							pr.setPayStatus((short)1);//付款状态，0为未付款，1为已付款，2为付款异常
							pr.setUpdateTime(updateTime);
							pr.setPayTime(updateTime);
							payRecordServiceApi.updateByPrimaryKeySelective(pr);
						}
						jsonResult.setSuccess(true);
						jsonResult.setStateCode(Const.SUCCESS_CODE);
						jsonResult.setComment("成功");
					}else{//支付失败
						shopOrder.setOrderStatus((short) 2);
						shopOrder.setUpdateTime(updateTime );
						shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
						//支付记录
						if(prllist!=null&&prllist.size()>0){
							PayRecord pr = prllist.get(0);
							pr.setPayStatus((short)2);//付款状态，0为未付款，1为已付款，2为付款异常
							pr.setUpdateTime(updateTime);
							pr.setPayTime(updateTime);
							payRecordServiceApi.updateByPrimaryKeySelective(pr);
						}
						jsonResult.setSuccess(false);
						jsonResult.setStateCode(Const.ERROR_CODE);
						jsonResult.setComment("失败");
					}
					
				}
			
				jsonResult.setDataObj(charge);
			}else{//接口查询失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
			}
			
		} catch (PingppException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}

		return jsonResult;
	}
	/**
	  * @Title: toRefund
	  * @Description: 退款
	  * @param chargeId 
	  * @param amount 退款金额退款的金额, 单位为对应币种的最小货币单位，例如：人民币为分（如退款金额为 1 元，此处请填 100）。必须小于等于可退款金额，默认为全额退款，应用内快捷支付目前只支持全额退款。
	  * @param description 退款详情，最多 255 个 Unicode 字符。
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 下午5:42:07
	 */
	@RequestMapping("/toRefund.do")
	@ResponseBody
	public JsonResult toRefund( @RequestBean String chargeId,@RequestBean Double amount,@RequestBean String description,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		if(description==null||description.equals(""))description="退款";
		try {
			// 参数处理
			if(chargeId==null||chargeId.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("参数为空");
				return jsonResult;
			}
			ShopOrder shopOrder = shopOrderServiceApi.selectByChargeId(chargeId);
			if(shopOrder==null){//数据库没有记录
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setComment("数据库没有记录");
				return jsonResult;
			}
			Pingpp.apiKey = Const.apiKey;
			Charge charge = Charge.retrieve(chargeId);
			Refund refund = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("description", description);
			if(amount!=null){
				params.put("amount", AmountUtils.changeY2F(amount+""));
			}
			refund = charge.getRefunds().create(params);
	        System.out.println(refund);
			Date updateTime = new Date();
			//System.out.println(charge);
			if(refund!=null){//查询到结果
				/*******对数据库进行操作***********/
				//向支付记录表中插记录
				String payno = "REFUND"+idWorkerServiceApi.nextId();
				PayRecord pr = new PayRecord();
				pr.setPayNo(payno);
				pr.setAccessUserAccountNumber("");//收款人账号
				pr.setAccessUserId((long) 0);//收款人ID，默认为0
				pr.setActualAmt(amount);//实际支付金额
				pr.setCreateTime(updateTime);
				pr.setDiscountAmt(0.0);
				pr.setOrderId(shopOrder.getId());
				pr.setOrderType((short)1);//付款订单类型，默认为0，0为O2O订单，1为商城订单
				pr.setPayableAmt(shopOrder.getOrderAmt());
				pr.setPayDescription("退款");
				pr.setPayTime(null);
				pr.setPayType((short)1);//付款类型，1为支付宝，2为微信
				pr.setUpdateTime(updateTime);
				pr.setUserAccountNumber("");//付款人账号，暂时为空
				pr.setUserId(shopOrder.getUserId());
				pr.setRecordType((short) 2);//记录类型1是支付2是退款
				pr.setObjid(refund.getId());
				//一般都是实时失败，执行退款查询时候会会显示正确结果
				if(refund.getSucceed()){//退款成功 
						
						pr.setPayStatus((short)1);//付款状态，0为未付款，1为已付款，2为付款异常
						shopOrder.setOrderStatus((short) 3);
						shopOrder.setUpdateTime(updateTime);
						shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
						jsonResult.setSuccess(true);
						jsonResult.setStateCode(Const.SUCCESS_CODE);
						jsonResult.setComment("退款成功");
					
				}else{//退款失败
					pr.setPayStatus((short)0);//付款状态，0为未付款，1为已付款，2为付款异常
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setComment("退款失败");
				}
				payRecordServiceApi.insertSelective(pr);
				jsonResult.setDataObj(refund);
			}else{//接口查询失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("接口查询失败");
			}
			
		} catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: findRefund
	  * @Description: 查询退款
	  * @param refundId 退款对象id
	  * @param chargeId 费用对象id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 下午5:54:05
	 */
	@RequestMapping("/findRefund.do")
	@ResponseBody
	public JsonResult findRefund( @RequestBean String refundId,@RequestBean String chargeId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		try {
			// 参数处理
			if(chargeId==null||chargeId.equals("")||refundId==null||refundId.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("参数为空");
				return jsonResult;
			}
			ShopOrder shopOrder = shopOrderServiceApi.selectByChargeId(chargeId);
			if(shopOrder==null){//数据库没有记录
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setComment("数据库没有记录");
				return jsonResult;
			}
			
			Pingpp.apiKey = Const.apiKey;
			Charge charge = Charge.retrieve(chargeId);
			Refund refund = charge.getRefunds().retrieve(refundId);
			
			System.out.println(refund);
			Date updateTime = new Date();
			//System.out.println(charge);
			if(refund!=null){//查询到结果
				/*******对数据库进行操作***********/

				if(refund.getSucceed()){//退款成功
					
					shopOrder.setOrderStatus((short) 3);
					shopOrder.setUpdateTime(updateTime);
					shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
					//退款记录
					Where where = new Where();
					Criteria criteria = where.createCriteria();
					criteria.andEqualTo("order_id", shopOrder.getId());
					criteria.andEqualTo("order_type", 1);
					criteria.andEqualTo("record_type", 2);
					criteria.andEqualTo("objid", refundId);
					
					List<PayRecord> prllist = payRecordServiceApi.selectByWhere(where);
					if(prllist!=null&&prllist.size()>0){
						PayRecord pr = prllist.get(0);
						pr.setPayStatus((short)1);//付款状态，0为未付款，1为已付款，2为付款异常
						pr.setUpdateTime(updateTime);
						pr.setPayTime(updateTime);
						payRecordServiceApi.updateByPrimaryKeySelective(pr);
					}
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setComment("退款成功");
					
				}else{//退款失败
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setComment("退款失败");
				}
				
				jsonResult.setDataObj(refund);
			}else{//接口查询失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
			}
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	
}
