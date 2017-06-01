package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.DecorationItemMaterialRelational;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface DecorationItemMaterialRelationalReaderMapper {
    int countByWhere(Where where);

    List<DecorationItemMaterialRelational> selectByWhere(Where where);

    DecorationItemMaterialRelational selectByPrimaryKey(Long id);
}