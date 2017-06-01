package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface SubscribeReaderMapper {
    int countByWhere(Where where);

    List<Subscribe> selectByWhere(Where where);

    Subscribe selectByPrimaryKey(Long id);
}