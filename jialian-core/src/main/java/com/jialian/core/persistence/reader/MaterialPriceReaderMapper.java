package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.MaterialPrice;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface MaterialPriceReaderMapper {
    int countByWhere(Where where);

    List<MaterialPrice> selectByWhere(Where where);

    MaterialPrice selectByPrimaryKey(Long id);
}