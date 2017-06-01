package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface SignedRecordReaderMapper {
    int countByWhere(Where where);

    List<SignedRecord> selectByWhere(Where where);

    SignedRecord selectByPrimaryKey(Long id);
}