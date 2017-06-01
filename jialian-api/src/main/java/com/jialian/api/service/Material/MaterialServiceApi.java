package com.jialian.api.service.Material;

import java.util.List;

import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.Where;

public interface MaterialServiceApi {

	int countByWhere(Where where);

    List<Material> selectByWhereWithBLOBs(Where where);

    List<Material> selectByWhere(Where where);

    Material selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    Material insert(Material record);

    Material insertSelective(Material record);

    int updateByWhereSelective(Material record, Where where);

    int updateByWhereWithBLOBs(Material record, Where where);

    int updateByWhere(Material record, Where where);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKeyWithBLOBs(Material record);

    int updateByPrimaryKey(Material record);
}
