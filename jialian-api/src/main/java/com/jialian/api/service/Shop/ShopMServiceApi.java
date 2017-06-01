package com.jialian.api.service.Shop;

import com.jialian.api.domain.basic.ServiceResult;

public interface ShopMServiceApi {
	
	// 商品管理
	ServiceResult<Object> findprodlistBywhere(String number, String name, Long twotype, Integer fbzt, Integer pageSize, Integer pageIndex);
}
