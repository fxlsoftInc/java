package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface HouseStructureInfoReaderMapper {
    int countByWhere(Where where);

    List<HouseStructureInfo> selectByWhere(Where where);

    HouseStructureInfo selectByPrimaryKey(Long id);
}