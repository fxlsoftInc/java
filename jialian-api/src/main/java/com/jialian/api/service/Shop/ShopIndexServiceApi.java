/**
 * 
 */package com.jialian.api.service.Shop;import com.jialian.api.domain.basic.ServiceResult;

/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月8日 下午3:03:37  */
/**
 *@Description: 商城首页
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月8日 下午3:03:37
 *@Version:1.0
 */
public interface ShopIndexServiceApi {

	/**
	  * @Title: getShopIndex
	  * @Description: 获取商城首页信息
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月8日 下午3:39:24
	 */
	ServiceResult<Object> getShopIndex();

	/**
	  * @param salessort  排序1是价格正序2是价格倒序
	 * @param salessort 排序1是销量正序2是销量倒序
	 * @param maxPrice 最小金额
	 * @param minPrice 最大金额
	 * @Title: getShopProduct
	  * @Description: 搜索商品+分页信息
	  * @param prodName 商品名称
	  * @param proTypeId 商品类型id
	  * @param pageSize 从哪开始
	  * @param pageIndex 偏移量
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月8日 下午6:00:04
	  */
	ServiceResult<Object> getShopProduct(Integer pricesort, Integer salessort, Double minPrice, Double maxPrice, String prodName, Long proTypeId,
			Integer pageSize, Integer pageIndex);

	/**
	  * @Title: getProdDetail
      * @Description: 查找商品详情
	  * @param prodId 商品id
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月9日 下午2:31:18
	  */
	ServiceResult<Object> getProdDetail(Long prodId);

	/**
	  * @Title: getShopProdTypes
	  * @Description: 获取商品类型
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月16日 下午6:39:33
	  */
	ServiceResult<Object> getShopProdTypes();

	/**
	  * @Title: getShopProdList
	  * @Description:
	  * @param pname
	  * @param pno
	  * @param type
	  * @param pubstatus
	  * @param pageSize
	  * @param pageIndex
	 * @param pageIndex2 
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月21日 下午3:11:53
	  */
	ServiceResult<Object> getShopProdList(String pname, String pno, Long type,
			Integer pubstatus, Integer recommend, Integer pageSize, Integer pageIndex);
}
