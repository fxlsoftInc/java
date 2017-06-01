package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseStyle;
import com.jialian.api.domain.entity.HouseStyleWithBLOBs;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.HouseStyleVo;

import java.util.List;

public interface HouseStyleReaderMapper {
    int countByWhere(Where where);

    List<HouseStyleWithBLOBs> selectByWhereWithBLOBs(Where where);

    List<HouseStyle> selectByWhere(Where where);

    HouseStyleWithBLOBs selectByPrimaryKey(Long id);
    
    List<HouseStyleVo> selectHouseStyleAll();
}