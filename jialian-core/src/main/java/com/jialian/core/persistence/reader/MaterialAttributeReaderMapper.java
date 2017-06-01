package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.Where;

import java.util.List;

public interface MaterialAttributeReaderMapper {
    int countByWhere(Where where);

    List<MaterialAttribute> selectByWhere(Where where);

    MaterialAttribute selectByPrimaryKey(Long id);

	/**
	  * @Title: selectShopProdByPrimaryKey
	  * @Description: 通过商品属性id查找商品的详细信息
	  * @param ptid
	  * @return  
	  * @return 返回类型  MaterialAttribute   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月11日 上午11:04:05
	  */
	MaterialAttribute selectShopProdByPrimaryKey(Long ptid);
}