package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface PayRecordReaderMapper {
    int countByWhere(Where where);

    List<PayRecord> selectByWhere(Where where);

    PayRecord selectByPrimaryKey(Long id);
    
    Double sumPayAmtByWhere(Where where);
}