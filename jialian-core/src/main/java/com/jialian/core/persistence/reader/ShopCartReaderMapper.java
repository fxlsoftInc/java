package com.jialian.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.ShopCart;
import com.jialian.api.domain.entity.Where;

public interface ShopCartReaderMapper {

    ShopCart selectByPrimaryKey(Long id);

	/**
	 *@Description: 查找用户的购物车
	 *@Author: zhls  联系方式:zhls1992@qq.com
	 *@Since: 2016年1月7日 下午1:49:45
	 *@Version:1.0
	 */
	
	List<ShopCart> selectByUserId(Long userId);
	
	List<ShopCart> selectByUserId_ids(@Param(value = "map") Map<String, Object> map);

	/**
	  * @Title: selectByUserIdProAttrId
	  * @Description: 根据用户id 商品属性id 查找购物车
	  * @param userId 用户id
	  * @param proAttrId 商品属性id
	  * @return  
	  * @return 返回类型  ShopCart   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月7日 下午6:01:53
	  */
	ShopCart selectByUserIdProAttrId(Where where);

	/**
	  * @Title: selectById
	  * @Description: 
	  * @param scid
	  * @return  
	  * @return 返回类型  ShopCart   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 上午11:39:12
	  */
	ShopCart selectById(Long scid);

}