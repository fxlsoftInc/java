package com.jialian.core.persistence.reader;

import java.util.List;

import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.Where;

public interface ShopOrderReaderMapper {

    ShopOrder selectByPrimaryKey(Long id);

	/**
	  * @Title: selectByOrderno
	  * @Description: 根据订单号查找订单
	  * @param out_trade_no
	  * @return  
	  * @return 返回类型  ShopOrder   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月12日 上午11:57:05
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
	  * @Since: 2016年1月12日 下午4:28:51
	  */
	ShopOrder selectByChargeId(String chargeId);

	/**
	  * @Title: selectByWhere
	  * @Description:
	  * @param where
	  * @return  
	  * @return 返回类型  List<ShopOrder>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 上午11:23:53
	  */
	List<ShopOrder> selectByWhere(Where where);

	/**
	  * @Title: countByWhere
	  * @Description: 
	  * @param where
	  * @return  
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月18日 上午11:24:00
	  */
	Integer countByWhere(Where where);


	

}