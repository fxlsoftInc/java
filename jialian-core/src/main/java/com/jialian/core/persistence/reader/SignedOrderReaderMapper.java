package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.DecOrderDetailVO;
import com.jialian.api.domain.vo.DecorationOrderVO;

import java.util.List;

public interface SignedOrderReaderMapper {
    int countByWhere(Where where);

    List<SignedOrder> selectByWhere(Where where);

    SignedOrder selectByPrimaryKey(Long id);
    
    List<DecorationOrderVO> selectSignedOrderList(Where where);
    
    int countSignedOrderByWhere(Where where);
    
    DecOrderDetailVO selectWithUserByPrimaryKey(Long id);
}