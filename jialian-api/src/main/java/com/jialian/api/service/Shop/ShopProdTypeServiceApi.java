/**
 * 
 */package com.jialian.api.service.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月20日 下午1:42:16  */

import java.util.List;

import com.jialian.api.domain.entity.ShopProdType;
import com.jialian.api.domain.entity.Where;

/**
 *@Description: 商城类型
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月20日 下午1:42:16
 *@Version:1.0
 */
public interface ShopProdTypeServiceApi {
	List<ShopProdType> selectByWhere(Where where);
	
	int countByWhere(Where where);
	
	ShopProdType selectByPrimaryKey(Long id);
	
	int deleteByPrimaryKey(Long id);
	
	int insertSelective(ShopProdType record);

	int updateByPrimaryKeySelective(ShopProdType record);
}
