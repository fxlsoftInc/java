package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SemiDecorationOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.SemDecorationOrderVO;

import java.util.List;

public interface SemiDecorationOrderReaderMapper {
    int countByWhere(Where where);

    List<SemiDecorationOrder> selectByWhere(Where where);

    SemiDecorationOrder selectByPrimaryKey(Long id);
    
    SemDecorationOrderVO selectSemDecOrderByPrimaryKey(Long id);
}