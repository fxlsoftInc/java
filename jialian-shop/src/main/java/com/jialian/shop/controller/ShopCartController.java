/**
 * 
 */package com.jialian.shop.controller;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 下午2:03:09  */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.ShopCart;
import com.jialian.api.service.Shop.ShopCartServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description:购物车
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 下午2:03:09
 *@Version:1.0
 */
 @Controller
public class ShopCartController {
	 
	 @Resource
	 private ShopCartServiceApi shopCartServiceApi;
	
	/**
	  * @Title: getShopCart
	  * @Description: 查询购物车
	  * @param userId 用户id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午4:48:42
	 */
	@RequestMapping("/getShopCart.do")
	@ResponseBody
	public JsonResult getShopCart(Long userId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//判断参数是否合法
		if(userId == null || userId <=0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			List<ShopCart> shopCartlist = shopCartServiceApi.selectByUserId(userId);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("查询成功");
			jsonResult.addData("shopCartlist", shopCartlist);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	
	/**
	  * @Title: getShopCart
	  * @Description: 购物车-结算
	  * @param userId 用户id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午4:48:42
	 */
	@RequestMapping("/getshopCartsum.do")
	@ResponseBody
	public JsonResult getshopCartsum(@RequestBean Long userId, @RequestBean String ids, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//判断参数是否合法
		if(userId == null || userId <=0 || ids == null || ids.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Integer> list = new ArrayList<Integer>();
			String[] xids = ids.split(",");
			for (int i = 0; i < xids.length; i++) {
				list.add(Integer.parseInt(xids[i]));
			}
			map.put("userId", userId);
			map.put("ids", list);
			List<ShopCart> shopCartlist = shopCartServiceApi.selectByUserId_ids(map);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("查询成功");
			jsonResult.addData("shopCartlist", shopCartlist);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		return jsonResult;
	}
	
	/**
	  * @Title: addShopCart
	  * @Description: 添加商品到购物车
	  * @param userId 用户id
	  * @param proId 商品id
	  * @param proAttrId 商品属性id
	  * @param count 商品数量
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午5:02:14
	 */
	@RequestMapping("/addShopCart.do")
	@ResponseBody
	public JsonResult addShopCart(@RequestBean Long userId,@RequestBean Long proId,@RequestBean Long proAttrId,@RequestBean Integer count, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//设置默认参数
		if(count == null || count <= 0) count=1;
		//判断参数是否合法
		if(userId == null || userId <=0 || proId == null || proId <=0 || proAttrId  == null || proAttrId <=0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Date createTime = new Date();
			int flag = shopCartServiceApi.addShopCart(userId,proId,proAttrId,count,createTime);
			if(flag==1){//添加成功
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("添加成功");
			}else if(flag==2){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.UNDERSTOCK_CODE);
				jsonResult.setMessage("添加失败，库存不足");
			}else{//添加失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("添加失败");
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
	  * @Title: deleteShopCart
	  * @Description: 删除购物车
	  * @param userId 用户id
	  * @param proAttrId 商品属性id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午6:39:50
	 */
	@RequestMapping("/deleteShopCart.do")
	@ResponseBody
	public JsonResult deleteShopCart(@RequestBean Long userId, @RequestBean Long proAttrId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//判断参数是否合法
		if(userId == null || userId <=0 || proAttrId  == null || proAttrId <=0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			int flag = shopCartServiceApi.deleteShopCart(userId,proAttrId);
			if(flag==1){//添加成功
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("删除成功");
			}else{//添加失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("删除失败");
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
	  * @Title: updateShopCart
	  * @Description: 修改购物车
	  * @param userId 用户id
	  * @param proAttrId 商品属性id
	  * @param count 数量
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午6:56:50
	 */
	@RequestMapping("/updateShopCart.do")
	@ResponseBody
	public JsonResult updateShopCart(@RequestBean Long userId, @RequestBean Long proAttrId,
			@RequestBean Integer count, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//设置默认参数
		if(count == null || count <= 0) count=1;
		//判断参数是否合法
		if(userId == null || userId <=0 || proAttrId  == null || proAttrId <=0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			int flag = shopCartServiceApi.updateShopCart(userId,proAttrId,count);
			if(flag==1){//添加成功
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("修改成功");
			}else if(flag==2){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.UNDERSTOCK_CODE);
				jsonResult.setMessage("修改失败，库存不足");
			}else if(flag==3){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
				jsonResult.setMessage("修改失败，没有该记录");
			}else{//添加失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("修改失败");
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
