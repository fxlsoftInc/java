package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.Memorando;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface MemorandoReaderMapper {
    int countByWhere(Where where);

    List<Memorando> selectByWhere(Where where);

    Memorando selectByPrimaryKey(Long id);
}