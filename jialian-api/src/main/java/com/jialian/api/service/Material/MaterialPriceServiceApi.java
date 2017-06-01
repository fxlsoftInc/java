package com.jialian.api.service.Material;

import java.util.List;

import com.jialian.api.domain.entity.MaterialPrice;
import com.jialian.api.domain.entity.Where;

public interface MaterialPriceServiceApi {

	int countByWhere(Where where);

    List<MaterialPrice> selectByWhere(Where where);

    MaterialPrice selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    MaterialPrice insert(MaterialPrice record);

    MaterialPrice insertSelective(MaterialPrice record);

    int updateByWhereSelective(MaterialPrice record, Where where);

    int updateByWhere(MaterialPrice record, Where where);

    int updateByPrimaryKeySelective(MaterialPrice record);

    int updateByPrimaryKey(MaterialPrice record);
}
