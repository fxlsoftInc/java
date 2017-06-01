package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseStructureCategory;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface HouseStructureCategoryReaderMapper {
    int countByWhere(Where where);

    List<HouseStructureCategory> selectByWhere(Where where);

    HouseStructureCategory selectByPrimaryKey(Long id);
    
    HouseStructureCategory selectWithHouseStruByPrimaryKey(Long id);
}