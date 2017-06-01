package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ShopOrderDetail;

public interface ShopOrderDetailWriterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrderDetail record);

    int insertSelective(ShopOrderDetail record);

    int updateByPrimaryKeySelective(ShopOrderDetail record);

    int updateByPrimaryKey(ShopOrderDetail record);
}