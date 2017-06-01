/**
 * 
 */package com.jialian.api.service.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 下午1:40:28  */

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jialian.api.domain.entity.ShopCart;

/**
 *@Description: 购物车
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 下午1:40:28
 *@Version:1.0
 */
public interface ShopCartServiceApi {
	
	/**
	 * 
	 *@Description: 查找用户的购物车
	 *@Author: zhls  联系方式:zhls1992@qq.com
	 *@Since: 2016年1月7日 下午1:43:59
	 *@Version:1.0
	 */
	List<ShopCart> selectByUserId(Long userId);
	
	List<ShopCart> selectByUserId_ids(Map<String, Object> map);

	/**
	  * @Title: addShopCart
	  * @Description: 添加商品到购物车
	  * @param userId 用户id
	  * @param proId 商品id
	  * @param proAttrId 商品属性id
	  * @param count 商品数量
	  * @param request
	  * @return  
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午5:16:14
	 */
	int addShopCart(Long userId, Long proId, Long proAttrId, Integer count,
			Date createTime);

	/**
	  * @Title: deleteShopCart
	  * @Description: 删除购物车
	  * @param userId 用户id
	  * @param proAttrId 商品属性id
	  * @return  
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午6:41:07
	  */
	int deleteShopCart(Long userId, Long proAttrId);

	/**
	  * @Title: updateShopCart
	 * @Description: 修改购物车
	  * @param userId 用户id
	  * @param proAttrId 商品属性id
	  * @param count 数量
	  * @return  
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午6:56:30
	  */
	int updateShopCart(Long userId, Long proAttrId, Integer count);

	/**
	  * @Title: selectById
	  * @Description:
	  * @param parseLong
	  * @return  
	  * @return 返回类型  ShopCart   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 上午11:38:04
	  */
	ShopCart selectById(Long parseLong);
	
}
