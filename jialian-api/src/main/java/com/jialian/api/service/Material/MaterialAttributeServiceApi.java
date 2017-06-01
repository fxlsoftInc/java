package com.jialian.api.service.Material;

import java.util.List;

import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.Where;

public interface MaterialAttributeServiceApi {

	int countByWhere(Where where);

    List<MaterialAttribute> selectByWhere(Where where);

    MaterialAttribute selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    MaterialAttribute insert(MaterialAttribute record);

    MaterialAttribute insertSelective(MaterialAttribute record);

    int updateByWhereSelective(MaterialAttribute record, Where where);

    int updateByWhere(MaterialAttribute record, Where where);

    int updateByPrimaryKeySelective(MaterialAttribute record);

    int updateByPrimaryKey(MaterialAttribute record);

	/**
	  * @Title: selectShopProdByPrimaryKey
	  * @Description: 通过商品属性id查找商品的详细信息
	  * @param parseLong
	  * @return  
	  * @return 返回类型  MaterialAttribute   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 上午11:01:28
	  */
	MaterialAttribute selectShopProdByPrimaryKey(Long ptid);
}
