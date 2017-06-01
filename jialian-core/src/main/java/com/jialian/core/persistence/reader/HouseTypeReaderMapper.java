package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface HouseTypeReaderMapper {
    int countByWhere(Where where);

    List<HouseType> selectByWhere(Where where);

    HouseType selectByPrimaryKey(Long id);
    
    List<HouseType> selectByHouseTypeNo(String houseTypeNo);
}