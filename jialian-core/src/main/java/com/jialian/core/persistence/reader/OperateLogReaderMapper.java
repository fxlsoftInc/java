package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.OperateLog;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface OperateLogReaderMapper {
    int countByWhere(Where where);

    List<OperateLog> selectByWhere(Where where);

    OperateLog selectByPrimaryKey(Long id);
}