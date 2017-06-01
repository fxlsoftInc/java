package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SubHouseInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface SubHouseInfoReaderMapper {
    int countByWhere(Where where);

    List<SubHouseInfo> selectByWhere(Where where);

    SubHouseInfo selectByPrimaryKey(Long id);
}