package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.MainMaterialOrder;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface MainMaterialOrderReaderMapper {
    int countByWhere(Where where);

    List<MainMaterialOrder> selectByWhere(Where where);

    MainMaterialOrder selectByPrimaryKey(Long id);
}