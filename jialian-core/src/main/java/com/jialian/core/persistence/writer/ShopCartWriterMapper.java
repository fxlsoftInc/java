package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ShopCart;

public interface ShopCartWriterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKey(ShopCart record);

	/**
	  * @Title: deleteByProdTypeId
	  * @Description: 根据用户id和商品属性id，删除购物车记录
	  * @param proAttrId  
	 * @param proAttrId 
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月9日 下午6:34:45
	  */
	int deleteByProdTypeId(Long userId, Long proAttrId);
}