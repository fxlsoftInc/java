package com.jialian.platform.controller.decoration;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.Memorando;
import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.SubHouseInfo;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.SignedOrderPara;
import com.jialian.api.domain.para.SignedRecordPara;
import com.jialian.api.domain.para.SubHouseInfoPara;
import com.jialian.api.domain.query.DecorationOrderQuery;
import com.jialian.api.domain.vo.DecOrderDetailVO;
import com.jialian.api.domain.vo.DecorationOrderVO;
import com.jialian.api.domain.vo.SubOrderInfoVO;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.Address.AddressServiceApi;
import com.jialian.api.service.Decoration.HouseSurveyServiceApi;
import com.jialian.api.service.Decoration.MemorandoServiceApi;
import com.jialian.api.service.Decoration.SignedRecordServiceApi;
import com.jialian.api.service.House.HouseTypeServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.api.service.Order.OrderTrackServiceApi;
import com.jialian.api.service.Order.PayRecordServiceApi;
import com.jialian.api.service.Order.SignedOrderServiceApi;
import com.jialian.api.service.Order.SubHouseInfoServiceApi;
import com.jialian.api.service.Order.SubOrderServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.platform.controller.BaseController;

/**
 * @Description:
 * @Param:
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2016年1月18日上午11:42:05
 * @Version:1.0
 */
@Controller
@RequestMapping("/decoration")
public class DecorationController extends BaseController{
	
	@Resource
	private HouseSurveyServiceApi houseSurveyService;
	
	@Resource
	private MemorandoServiceApi memorandoService;
	
	@Resource
	private SignedRecordServiceApi signedRecordService;
	
	@Resource
	private SignedOrderServiceApi signedOrderService;
	
	@Resource
	private SubOrderServiceApi subOrderService;
	
	@Resource
	private PayRecordServiceApi payRecordService;
	
	@Resource
	private UserServiceApi userService;
	
	@Resource
	private OrderTrackServiceApi orderTrackService;
	
	@Resource
	private SubHouseInfoServiceApi subHouseInfoService;
	
	@Resource
	private AddressServiceApi addressService;
	
	@Resource
	private HouseTypeServiceApi houseTypeService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	
	@Resource
	private LogServiceApi logService;
	/**
	 * @Description:
	 * @Param:@param query
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午2:09:45
	 * @Version:1.0
	 */
	@RequestMapping("/getSignedOrderList.do")
	@ResponseBody
	public JsonResult getSignedOrderList(@RequestBean DecorationOrderQuery query,
			@RequestParam(value = "startIndex", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
		JsonResult result = new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		Criteria criteria = where.createCriteria();
		if(query != null && query.getComboId() != null){
			criteria.andEqualTo("so.combo_id", query.getComboId());
		}
		if(query != null && query.getEmployeeId() != null){
			criteria.andEqualTo("so.employee_id", query.getEmployeeId());
		}
		if(query != null && query.getOrderNo() != null){
			criteria.andLike("so.order_no", "%" + query.getOrderNo() + "%");
		}
		if(query != null && query.getOrderStatus() != null){
			criteria.andEqualTo("so.order_status", query.getOrderStatus());
		}
		if(query != null && query.getTelphone() != null){
			criteria.andLike("u.telephone", "%" + query.getTelphone() + "%");
		}
		criteria.andEqualTo("so.status", 1);
		List<DecorationOrderVO> decOrderList = signedOrderService.selectSignedOrderList(where);
		Integer count = signedOrderService.countSignedOrderByWhere(where);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("查询成功");
		result.addData("decOrderList", decOrderList);
		result.addData("count", count);
		return result;
	}
	
	/**
	 * @Description:
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午2:53:19
	 * @Version:1.0
	 */
	@RequestMapping("/getSignedOrderById.do")
	@ResponseBody
	public JsonResult getSignedOrderById(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <= 0){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		DecOrderDetailVO order = signedOrderService.selectWithUserByPrimaryKey(id);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.addData("order", order);
		return result;
	}
	
	/**
	 * @Description:
	 * @Param:@param orderNo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午4:21:15
	 * @Version:1.0
	 */
	@RequestMapping("/getSubOrderInfoByOrderNo.do")
	@ResponseBody
	public JsonResult getSubOrderInfoByOrderNo(String orderNo){
		JsonResult result = new JsonResult();
		if(orderNo == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		SubOrderInfoVO subOrderInfo = subOrderService.selectByOrderNo(orderNo.trim());
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.addData("subOrderInfo", subOrderInfo);
		return result;
	}
	
	/**
	 * @Description:
	 * @Param:@param orderNo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午6:14:55
	 * @Version:1.0
	 */
	@RequestMapping("/getSignedRecordListByOrderNo.do")
	@ResponseBody
	public JsonResult getSignedRecordListByOrderNo(String orderNo){
		JsonResult result = new JsonResult();
		if(orderNo == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("order_no", orderNo);
		List<SignedRecord> signedOrder = signedRecordService.selectByWhere(where);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.addData("signedOrder", signedOrder);
		return result;
	}
	
	/**
	 * memorandoService
	 * @Description:
	 * @Param:@param orderNo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午6:16:01
	 * @Version:1.0
	 */
	@RequestMapping("/getMemorandoListByOrderNo.do")
	@ResponseBody
	public JsonResult getMemorandoListByOrderNo(String orderNo){
		JsonResult result = new JsonResult();
		if(orderNo == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("order_no", orderNo);
		List<Memorando> memList = memorandoService.selectByWhere(where);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.addData("memList", memList);
		return result;
	}
	
	/**
	 * payRecordService
	 * @Description:
	 * @Param:@param orderNo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月18日下午6:16:01
	 * @Version:1.0
	 */
	@RequestMapping("/getPayRecordListByOrderNo.do")
	@ResponseBody
	public JsonResult getPayRecordListByOrderNo(String orderNo){
		JsonResult result = new JsonResult();
		if(orderNo == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("order_no", orderNo);
		List<PayRecord> payRecord = payRecordService.selectByWhere(where);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.addData("payRecord", payRecord);
		return result;
	}
	
	/**
	 * @Description:修改简约订单的详情
	 * @Param:@param para
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月19日下午6:02:44
	 * @Version:1.0
	 */
	@RequestMapping("/updateSignedOrder.do")
	@ResponseBody
	public JsonResult updateSignedOrder(@RequestBean SignedOrderPara para){
		JsonResult result = new JsonResult();
		if(para == null || para.getOrderId() == null || para.getUserId() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		SignedOrder signedOrder = new SignedOrder();
		signedOrder.setId(para.getOrderId());
		if(para.getComboId() != null){
			signedOrder.setEmployeeId(para.getEmployeeId());
		}
		if(para.getComboId() != null){
			signedOrder.setComboId(para.getComboId());
		}
		if(para.getOrderStatus() != null){
			signedOrder.setOrderStatus(para.getOrderStatus());
		}
		signedOrderService.updateByPrimaryKeySelective(signedOrder);
		signedOrder = signedOrderService.selectByPrimaryKey(signedOrder.getId());
		User user = new User();
		user.setId(para.getUserId());
		if(para.getTelephone() != null){
			user.setTelephone(para.getTelephone());
		}
		if(para.getNickName() != null){
			user.setNickName(para.getNickName());
		}
		if(para.getSex() != null){
			if(para.getSex() != 1){
				para.setSex((short)0);
			}
			user.setSex(para.getSex());
		}
		userService.updateByPrimaryKeySelective(user);
		OrderTrack orderTrack = new OrderTrack();
		orderTrack.setUserId(user.getId());
		orderTrack.setOrderId(signedOrder.getId());
		orderTrack.setTrackTitle("装修订单基础信息更改");
		String trackContent;
		if(user.getSex() == 0){
			trackContent =  user.getNickName() + " 女士，您好！你的订单" + signedOrder.getOrderNo() +" 基本信息发生了改变，请查看！";
		}else{
			trackContent =  user.getNickName() + " 先生，您好！你的订单" + signedOrder.getOrderNo() +" 基本信息发生了改变，请查看！";
		}
		orderTrack.setTrackContent(trackContent);
		orderTrack.setTrackId(signedOrder.getId());
		orderTrack.setTrackType((short)3);
		orderTrackService.insertSelective(orderTrack);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("设置保存成功");
		
		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "修改装修订单信息");
		return result;
	}
	
	/**
	 * @Description:修改签约订单的房屋信息
	 * @Param:@param para
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月19日下午6:59:48
	 * @Version:1.0
	 */
	@RequestMapping("/updateSubHouseInfo.do")
	@ResponseBody
	public JsonResult updateSubHouseInfo(@RequestBean SubHouseInfoPara para){
		JsonResult result = new JsonResult();
		if(para == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		//修改主表
		SubHouseInfo subHouseInfo = new SubHouseInfo();
		subHouseInfo.setId(para.getId());
		subHouseInfo.setArea(para.getArea());
		subHouseInfo.setType(para.getType());
		subHouseInfo.setPlanDecorationTime(para.getPlanDecorationTime());
		subHouseInfo.setHouseStyleId(para.getHouseStyleId());
		subHouseInfo.setMeasurer(para.getMeasurer());
		subHouseInfo.setMeasurerTelephone(para.getMeasurerTelephone());
		subHouseInfoService.updateByPrimaryKeySelective(subHouseInfo);
		//修改Address
		addressService.updateByPrimaryKeySelective(para.getAddress());
		//修改房屋信息具体数据
		for(HouseType type: para.getHouseTypeList()){
			houseTypeService.updateByPrimaryKeySelective(type);
		}
		//跟踪信息
		User user = userService.selectByPrimaryKey(para.getUserId());
		String trackContent;
		OrderTrack orderTrack = new OrderTrack();
		if(para.getIsActual()){
			if(user.getSex() == 0){
				trackContent =  user.getNickName() + " 女士，您好！你的订单" + para.getOrderNo() +" 实际量房信息发生了改变，请查看！";
			}else{
				trackContent =  user.getNickName() + " 先生，您好！你的订单" + para.getOrderNo() +" 实际量房信息发生了改变，请查看！";
			}
			orderTrack.setTrackTitle("实际量房信息发生更改");
		}else{
			if(user.getSex() == 0){
				trackContent =  user.getNickName() + " 女士，您好！你的订单" + para.getOrderNo() +" 预约房屋信息发生了改变，请查看！";
			}else{
				trackContent =  user.getNickName() + " 先生，您好！你的订单" + para.getOrderNo() +" 预约房屋信息发生了改变，请查看！";
			}
			orderTrack.setTrackTitle("预约房屋信息发生更改");
		}
		orderTrack.setUserId(user.getId());
		orderTrack.setOrderId(para.getOrderId());
		orderTrack.setTrackContent(trackContent);
		orderTrack.setTrackId(para.getId());
		orderTrack.setTrackType((short)4);
		orderTrackService.insertSelective(orderTrack);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("设置保存成功");
		

		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "修改装修订单房屋信息");
		return result;
	}
	
	/** 
	 * @Description:签约订单的修改
	 * @Param:@param record
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月20日下午12:00:25
	 * @Version:1.0
	 */
	@RequestMapping("/updateSignedRecord.do")
	@ResponseBody
	public JsonResult updateSignedRecord(@RequestBean SignedRecordPara record){
		JsonResult result = new JsonResult();
		if(record == null || record.getId() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		SignedRecord signedRecord = new SignedRecord();
		signedRecord.setId(record.getId());
		if(record.getContractAmt() != null){
			signedRecord.setContractAmt(record.getContractAmt());
		}
		if(record.getSignedTime() != null){
			signedRecord.setSignedTime(record.getSignedTime());
		}
		if(record.getContractStartTime() != null){
			signedRecord.setContractStartTime(record.getContractStartTime());
		}
		if(record.getContractEndTime() != null){
			signedRecord.setContractEndTime(record.getContractEndTime());
		}
		if(record.getContractFirstAmt() != null){
			signedRecord.setContractFirstAmt(record.getContractFirstAmt());
		}
		if(record.getContractLastAmt() != null){
			signedRecord.setContractLastAmt(record.getContractLastAmt());
		}
		if(record.getContractLastCondition() != null){
			signedRecord.setContractLastCondition(record.getContractLastCondition());
		}
		if(record.getContractFirstCondition() != null){
			signedRecord.setContractFirstCondition(record.getContractFirstCondition());
		}
		signedRecordService.updateByPrimaryKeySelective(signedRecord);
		// 跟踪信息
		User user = userService.selectByPrimaryKey(record.getUserId());
		String trackContent;
		OrderTrack orderTrack = new OrderTrack();
		if (user.getSex() == 0) {
			trackContent = user.getNickName() + " 女士，您好！你的订单" + record.getOrderNo() + " 签约合同信息发生了改变，请查看！";
		} else {
			trackContent = user.getNickName() + " 先生，您好！你的订单" + record.getOrderNo() + " 签约合同信息发生了改变，请查看！";
		}
		orderTrack.setTrackTitle("签约合同信息发生更改");

		orderTrack.setUserId(user.getId());
		orderTrack.setOrderId(record.getOrderId());
		orderTrack.setTrackContent(trackContent);
		orderTrack.setTrackId(record.getId());
		orderTrack.setTrackType((short) 5);
		orderTrackService.insertSelective(orderTrack);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("设置保存成功");
		

		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "修改装修订单签约合同信息");
		return result;
	}
	
	/**
	 * @Description:添加支付记录
	 * @Param:@param record
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月20日下午1:43:38
	 * @Version:1.0
	 */
	@RequestMapping("/addPayRecord.do")
	@ResponseBody
	public JsonResult addPayRecord(@RequestBean PayRecord record){
		JsonResult result = new JsonResult();
		if(record == null || record.getId() != null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		record.setPayNo("PAY" + idWorker.nextId());
		record = payRecordService.insertSelective(record);
		User user = userService.selectByPrimaryKey(record.getUserId());
		String trackContent;
		OrderTrack orderTrack = new OrderTrack();
		if (user.getSex() == 0) {
			trackContent = user.getNickName() + " 女士，您好！你的订单" + record.getOrderNo() + " 有一条款项需要支付，请查看！";
		} else {
			trackContent = user.getNickName() + " 先生，您好！你的订单" + record.getOrderNo() + " 有一条款项需要支付，请查看！";
		}
		orderTrack.setTrackTitle("支付款项");
		orderTrack.setUserId(user.getId());
		orderTrack.setOrderId(record.getId());
		orderTrack.setTrackContent(trackContent);
		orderTrack.setTrackId(record.getId());
		orderTrack.setTrackType((short) 1);
		orderTrackService.insertSelective(orderTrack);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("设置保存成功");
		result.addData("payRecord", record);
		

		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "添加一条支付记录");
		return result;
	}
	
	/**
	 * @Description:添加备忘记录
	 * @Param:@param para
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月20日下午2:19:15
	 * @Version:1.0
	 */
	@RequestMapping("/addMemorando.do")
	@ResponseBody
	public JsonResult addMemorando(@RequestBean Memorando para){
		JsonResult result = new JsonResult();
		if(para == null || para.getId() != null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		para.setMemoNo("MEMO" + idWorker.nextId());
		para.setStatus((short)1);
		para = memorandoService.insertSelective(para);
		para.setCreateTime(new Date());
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("设置保存成功");
		result.addData("memo", para);
		

		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "添加一条备忘录");
		return result;
	}
	
	/**
	 * @Description:删除备忘记录
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月20日下午2:18:28
	 * @Version:1.0
	 */
	@RequestMapping("/deleteMemorando.do")
	@ResponseBody
	public JsonResult deleteMemorando(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <= 0){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		Memorando memo = new Memorando();
		memo.setId(id);
		memo.setStatus((short)0);
		memorandoService.updateByPrimaryKeySelective(memo);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("删除成功");
		

		//记入日志数据里
		AdminUser adminUser = getAdminUser(getRequest());
		logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "装修订单", "删除一条备忘记录");
		return result;
	}
	
	/**
	 * @Description:删除签约订单
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月20日下午2:23:20
	 * @Version:1.0
	 */
	@RequestMapping("/deleteSignedOrder.do")
	@ResponseBody
	public JsonResult deleteSignedOrder(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <= 0){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		SignedOrder order = new SignedOrder();
		order.setId(id);
		order.setStatus((short)0);
		signedOrderService.updateByPrimaryKeySelective(order);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("删除成功");
		return result;
	}
}
