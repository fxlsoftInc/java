package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SemiDecorationOrderInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.SemDecOrderInfoVO;

import java.util.List;

public interface SemiDecorationOrderInfoReaderMapper {
    int countByWhere(Where where);

    List<SemiDecorationOrderInfo> selectByWhere(Where where);

    SemiDecorationOrderInfo selectByPrimaryKey(Long id);
    
    List<SemDecOrderInfoVO> selectSemiDecOrderInfo(Long id);
}