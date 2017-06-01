/**
 * 
 */package com.jialian.shop.controller;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 下午2:03:09  */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.service.Shop.ShopIndexServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description:商城首页
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 下午2:03:09
 *@Version:1.0
 */
 @Controller
public class ShopIndexController {
	 
	 @Resource
	 private ShopIndexServiceApi shopIndexServiceApi;
	 /**
	   * @Title: getShopProdTypes
	   * @Description: 获取商品类型
	   * @param request
	   * @return  
	   * @return 返回类型  JsonResult   
	   * @throws
	   * @Author: zhls  联系方式：zhls1992@qq.com
	   * @Since: 2016年1月16日 下午6:40:08
	  */
	 @RequestMapping("/getShopProdTypes.do")
		@ResponseBody
		public JsonResult getShopProdTypes(HttpServletRequest request){
			JsonResult jsonResult = new JsonResult();
			
			try {
				ServiceResult<Object> result = shopIndexServiceApi.getShopProdTypes();
				jsonResult.setData(result.getDataMap());
				jsonResult.setSuccess(result.isOk());
				jsonResult.setStateCode(result.getMsgCode());
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("服务异常");
			}
			
			return jsonResult;
		}
	
	/**
	  * @Title: getShopIndex
	  * @Description: 商城首页
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午4:48:42
	 */
	@RequestMapping("/getShopIndex.do")
	@ResponseBody
	public JsonResult getShopIndex(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		try {
			ServiceResult<Object> result = shopIndexServiceApi.getShopIndex();
			jsonResult.setData(result.getDataMap());
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: getShopProducts
	  * @Description: 搜索商品
	  * @param prodName 商品名
	  * @param proTypeId 所属类型
	  * @param pageSize 每页记录
	  * @param pricesort 按照价格排序 1正序2倒序
	  * @param salessort 按照销量排序1正序2倒序
	  * @param minPrice 最小价格
	  * @param maxPrice 最大价格
	  * @param pageIndex 当前页
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午4:48:42
	 */
	@RequestMapping("/getShopProducts.do")
	@ResponseBody
	public JsonResult getShopProducts(@RequestBean Integer pricesort,@RequestBean Integer salessort, @RequestBean Double minPrice, @RequestBean Double maxPrice, @RequestBean String prodName, @RequestBean Long proTypeId, @RequestBean Integer pageSize, @RequestBean Integer pageIndex, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		try {
			ServiceResult<Object> result = shopIndexServiceApi.getShopProduct(pricesort,salessort,minPrice,maxPrice,prodName,proTypeId,pageSize,pageIndex);
			jsonResult.addData("pageData",result.getData());
			jsonResult.addData("params",result.getMsgParams());
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: getProdDetail
	  * @Description: 查找商品详情
	  * @param prodId 商品id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月9日 下午2:31:41
	 */
	@RequestMapping("/getProdDetail.do")
	@ResponseBody
	public JsonResult getProdDetail(Long prodId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		try {
			ServiceResult<Object> result = shopIndexServiceApi.getProdDetail(prodId);
			jsonResult.addData("pageData",result.getData());
			jsonResult.addData("params",result.getMsgParams());
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
}
