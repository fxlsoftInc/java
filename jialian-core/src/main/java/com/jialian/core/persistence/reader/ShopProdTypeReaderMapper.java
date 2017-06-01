package com.jialian.core.persistence.reader;

import java.util.List;

import com.jialian.api.domain.entity.ShopProdType;
import com.jialian.api.domain.entity.Where;

public interface ShopProdTypeReaderMapper {
	
    ShopProdType selectByPrimaryKey(Long id);

    int countByWhere(Where where);

    List<ShopProdType> selectByWhere(Where where);
}