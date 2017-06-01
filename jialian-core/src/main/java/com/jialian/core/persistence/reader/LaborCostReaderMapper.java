package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.LaborCost;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface LaborCostReaderMapper {
    int countByWhere(Where where);

    List<LaborCost> selectByWhere(Where where);

    LaborCost selectByPrimaryKey(Long id);
}