package com.jialian.core.service.impl.Shop;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Shop.ShopMServiceApi;

@Service("shopMService")
public class ShopMServiceImpl implements ShopMServiceApi {
	
	@Override
	public ServiceResult<Object> findprodlistBywhere(String number, String name, Long twotype, Integer fbzt, Integer pageSize,
			Integer pageIndex) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 20;
		}
		try {
			Where where = new Where(pageSize, pageIndex, "createTime desc");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("materialType", 3);
			criteria.andEqualTo("status", 1);
			if (number != null && !number.equals("")) {
				criteria.andLike("materialNo", number);
			}
			if (name != null && !name.equals("")) {
				criteria.andLike("materialName", name);
			}
			if (twotype != null && twotype != 0l) {
				criteria.andEqualTo("", twotype);
			}
			if (fbzt != null && fbzt != 0) {
				criteria.andEqualTo("", twotype);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		return result;
	}
	
	
}
