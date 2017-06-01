package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ShopOrder;

public interface ShopOrderWriterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}