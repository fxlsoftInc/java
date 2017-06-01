package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.MainMaterialOrderInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface MainMaterialOrderInfoReaderMapper {
    int countByWhere(Where where);

    List<MainMaterialOrderInfo> selectByWhere(Where where);

    MainMaterialOrderInfo selectByPrimaryKey(Long id);
}