package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.CaseHouseInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface CaseHouseInfoReaderMapper {
    int countByWhere(Where where);

    List<CaseHouseInfo> selectByWhere(Where where);

    CaseHouseInfo selectByPrimaryKey(Long id);
}