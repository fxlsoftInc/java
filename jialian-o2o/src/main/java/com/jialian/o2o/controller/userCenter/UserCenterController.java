package com.jialian.o2o.controller.userCenter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.vo.CollectVo;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.StringUtils;

/**
 *@Description: 个人中心相关操作控制器
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月14日 下午7:22:34
 *@Version:1.0
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController {

	@Resource
	private UserServiceApi userService;
	@Resource
	private ShopOrderServiceApi shopOrderServiceApi;
	/**
	 *@Description:  个人中心修改密码
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月14日 下午7:32:00
	 *@Version:1.0
	 */
	@RequestMapping("/changePsd.do")
	@ResponseBody
	public JsonResult changePsd(@RequestBean String mobile,@RequestBean String oldPsd,@RequestBean String newPsd){
		JsonResult result = new JsonResult();
		if(StringUtils.isStrNull(mobile)){
			result.setSuccess(false);
			result.setMessage("电话号码不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
		}else if(StringUtils.isStrNull(oldPsd)){
			result.setSuccess(false);
			result.setMessage("旧密码不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
		}else if(StringUtils.isStrNull(newPsd)){
			result.setSuccess(false);
			result.setMessage("新密码不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
		}else{
			User user = userService.userLogin(mobile, oldPsd);
			if(user == null || user.equals("")){
				result.setSuccess(false);
				result.setMessage("旧密码错误,修改失败");
				result.setStateCode(Const.ERROR_CODE);
			}else{//修改密码
				ServiceResult<User> serviceResult = userService.updateUserPsd(mobile,newPsd);
				if(serviceResult.isOk()){
					result.setSuccess(true);
					result.setMessage("恭喜,密码修改成功");
					result.setStateCode(Const.SUCCESS_CODE);
					result.setData(serviceResult.getDataMap());
				}else{
					result.setSuccess(false);
					result.setMessage("抱歉，密码修改失败");
					result.setStateCode(Const.ERROR_CODE);
				}
			}
		}
		return result;
	}
	
	/**
	 *@Description:  个人中心--联系人地址列表
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月15日 下午2:35:17
	 *@Version:1.0
	 */
	@RequestMapping("/selectContactAddressByUserId.do")
	@ResponseBody
	public JsonResult selectContactAddressByUserId(@RequestBean String mobile, HttpServletRequest request){
		JsonResult result = new JsonResult();
		if(StringUtils.isStrNull(mobile)){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("不存在该用户");
		}else{
		User user = userService.selectUserByMobile(mobile);
		if(user == null || user.equals("")){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_NODATA_CODE);
			result.setMessage("不存在该用户");
		}else{
			ServiceResult<List<Address>> serviceResult = userService.selectUserAddressByUserNo(user);
			if(serviceResult.isOk()){//存在地址
				result.setSuccess(true);
				result.setStateCode(Const.SUCCESS_CODE);
				if(serviceResult.getData() != null){
					result.setMessage("查询成功");
					result.addData("addressList", serviceResult.getData());
				}else{
					result.setMessage("您还没有收货地址哦~");
				}
			}else{//不存在地址
				result.setSuccess(false);
				result.setMessage("您还没有收货地址哦~");
				result.setStateCode(Const.ERROR_CODE);
			}
		 }
	   }
	return result;
  }
		
	
	/**
	 *@Description: 个人中心--添加联系人地址
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月16日 上午10:46:06
	 *@Version:1.0
	 */
	@RequestMapping("/addUserAddress.do")
	@ResponseBody
	public JsonResult addUserAddress(@RequestBean String mobile,@RequestBean Address address, HttpServletRequest request){
		JsonResult result = new JsonResult();
		if(StringUtils.isStrNull(mobile)){//为空返回
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("该用户不存在");
		}else{//不为空
			ServiceResult<Address> serviceResult = userService.addAddress(mobile,address);
			if(serviceResult.isOk()){
				result.setSuccess(true);
				result.setMessage(serviceResult.getComment());
				result.setStateCode(Const.SUCCESS_CODE);
			}else{
				result.setSuccess(false);
				result.setMessage(serviceResult.getComment());
				result.setStateCode(Const.ERROR_CODE);
			}
		}
		return result;
	}
	
	/**
	 * 删除联系地址
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 上午11:49:27
	 *@Version:1.0
	 */
	@RequestMapping("/deleteAddressById.do")
	@ResponseBody
	public JsonResult deleteAddressById(@RequestBean Long id, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			int result = userService.deleteAddressById(id);
			if(result == 1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("删除成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("删除失败");
			}
		}
		return jsonResult;
	}
	
	/**
	 * o2o-- 联系人地址详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月30日 上午11:38:31
	 *@Version:1.0
	 */
	@RequestMapping("/selectAddressDetailById.do")
	@ResponseBody
	public JsonResult selectAddressDetailById(@RequestBean Long id,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setMessage("参数错误");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
		}else{
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("id", id);
			List<Address> addressList = userService.selectAddressDetailById(where);
			if(addressList !=null && !addressList.equals("")){//查询成功
				Address address = addressList.get(0);
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.setDataObj(address);
			}else{//查询失败
				jsonResult.setSuccess(false);
				jsonResult.setMessage("该地址已被删除或不存在");
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			}
		}
		return jsonResult;
	}
	
	/**
	 * o2o 个人中心--我的收藏列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月17日 下午5:40:42
	 *@Version:1.0
	 */
	@RequestMapping("/selectCollectList.do")
	@ResponseBody
	public JsonResult selectCollectList(@RequestBean String mobile,HttpServletRequest request){
		JsonResult result = new JsonResult();
		if(StringUtils.isStrNull(mobile)){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("该用户不存在");
		}else{
			User user = userService.selectUserByMobile(mobile);
			/*User user = new User();
			user.setTelephone("13052315610");
			user.setId(3l);*/
			if(user == null || user.equals("")){
				result.setSuccess(false);
				result.setStateCode(Const.ERROR_RECORD_DELETED);
				result.setMessage("该用户不存在或已删除");
			}else{
				ServiceResult<List<CollectVo>> serviceResult = userService.selectCollectList(user);
				if(serviceResult.isOk()){
					result.setSuccess(true);
					result.setStateCode(Const.SUCCESS_CODE);
					result.setMessage("查询成功");
					result.addData("collectList", serviceResult.getData());
				}else{
					result.setSuccess(false);
					result.setStateCode(Const.ERROR_UNKNOWN_CODE);
					result.setMessage("未知错误");
				}
			}
		}
		return result;
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
				jsonResult.setComment("成功");
				jsonResult.setDataObj(result);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
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
