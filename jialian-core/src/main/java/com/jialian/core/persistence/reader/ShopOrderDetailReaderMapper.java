package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.ShopOrderDetail;

public interface ShopOrderDetailReaderMapper {

    ShopOrderDetail selectByPrimaryKey(Long id);

    List<ShopOrderDetail> selectByOrderId(@Param("orderId") Long orderId);
}