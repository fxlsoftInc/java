/**
 * 
 */package com.jialian.api.service.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月9日 下午5:35:59  */

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.Where;

/**
 *@Description: 商城订单
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月9日 下午5:35:59
 *@Version:1.0
 */
public interface ShopOrderServiceApi {

	int updateByPrimaryKeySelective(ShopOrder record);
	int deleteByPrimaryKey(Long id);
	ShopOrder selectByPrimaryKey(Long id);
	/**
	  * @Title: insert
	  * @Description: 
	  * @param shopOrder  
	  * @return 返回类型  void   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 下午12:04:16
	  */
	int insert(ShopOrder shopOrder);

	/**
	  * @Title: addShopOrder
	  * @Description:  提交订单
	  * @param userId
	  * @param totalAmt
	  * @param postage
	  * @param discountAmt
	  * @param orderAmt
	  * @param prepayAmt
	  * @param payedAmt
	  * @param useraddr
	  * @param addrid
	  * @param ispinkageAmt
	  * @param shopCartIds
	 * @param orderNo 
	 * @param ptype 
	 * @param linkuser 
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 下午1:42:09
	  */
	ServiceResult<Object> addShopOrder(Long userId, Double totalAmt,
			Double postage, Double discountAmt, Double orderAmt,
			Double prepayAmt, Double payedAmt, String useraddr, Long addrid,
			Short ispinkageAmt, String shopCartIds, String orderNo, Integer ptype, String linktel, String linkuser);
	/**
	  * @Title: selectByOrderno
	  * @Description: 根据订单号查找订单
	  * @param out_trade_no
	  * @return  
	  * @return 返回类型  ShopOrder   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 上午11:56:15
	  */
	ShopOrder selectByOrderno(String out_trade_no);
	/**
	  * @Title: selectByChargeId
	  * @Description: 根据chargeId查找shopOrder
	  * @param chargeId
	  * @return  
	  * @return 返回类型  ShopOrder   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 下午4:27:14
	  */
	ShopOrder selectByChargeId(String chargeId);
	/**
	  * @Title: selectByUserId_Orderno
	  * @Description:  获取个人商城订单
	  * @param userId
	  * @param orderno
	  * @param pageIndex
	  * @param pageSize
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 上午11:07:45
	  */
	ServiceResult<Object> selectByUserId_Orderno(Long userId, String orderno,
			Integer pageIndex, Integer pageSize);
	/**
	  * @Title: getShopOrderList
	  * @Description:
	  * @param orderno
	  * @param orderstatus
	  * @param telephone
	  * @param stime
	  * @param etime
	  * @param pageSize
	  * @param pageIndex
	  * @return  
	  * @return 返回类型  ServiceResult<Object>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月23日 下午5:51:43
	  */
	ServiceResult<Object> getShopOrderList(String orderno, Short orderstatus,
			String telephone, String stime, String etime, Integer pageSize,
			Integer pageIndex);
	
	Integer countByWhere(Where where);
}
