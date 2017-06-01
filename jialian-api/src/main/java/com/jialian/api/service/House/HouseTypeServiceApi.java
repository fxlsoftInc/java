package com.jialian.api.service.House;

import java.util.List;

import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.Where;

public interface HouseTypeServiceApi {
	
	int countByWhere(Where where);

    List<HouseType> selectByWhere(Where where);

    HouseType selectByPrimaryKey(Long id);
    
    List<HouseType> selectByHouseTypeNo(String houseTypeNo);
	
	int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    HouseType insert(HouseType record);

    HouseType insertSelective(HouseType record);

    int updateByWhereSelective(HouseType record, Where where);

    int updateByWhere(HouseType record, Where where);

    int updateByPrimaryKeySelective(HouseType record);

    int updateByPrimaryKey(HouseType record);
}
