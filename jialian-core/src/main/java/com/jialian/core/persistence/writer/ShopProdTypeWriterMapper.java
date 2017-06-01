package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ShopProdType;

public interface ShopProdTypeWriterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopProdType record);

    int insertSelective(ShopProdType record);

    int updateByPrimaryKeySelective(ShopProdType record);

    int updateByPrimaryKey(ShopProdType record);
}