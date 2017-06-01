package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface HouseInfoReaderMapper {
    int countByWhere(Where where);

    List<HouseInfo> selectByWhere(Where where);

    HouseInfo selectByPrimaryKey(Long id);
}