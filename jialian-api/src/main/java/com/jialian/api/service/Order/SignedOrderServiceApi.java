package com.jialian.api.service.Order;

import java.util.List;

import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.DecOrderDetailVO;
import com.jialian.api.domain.vo.DecorationOrderVO;

public interface SignedOrderServiceApi {
	
	int countByWhere(Where where);

    List<SignedOrder> selectByWhere(Where where);

    SignedOrder selectByPrimaryKey(Long id);
    
    DecOrderDetailVO selectWithUserByPrimaryKey(Long id);
    
    List<DecorationOrderVO> selectSignedOrderList(Where where);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    SignedOrder insert(SignedOrder record);

    SignedOrder insertSelective(SignedOrder record);

    int updateByWhereSelective(SignedOrder record, Where where);

    int updateByWhere(SignedOrder record, Where where);

    int updateByPrimaryKeySelective(SignedOrder record);

    int updateByPrimaryKey(SignedOrder record);
    
    int countSignedOrderByWhere(Where where);
}
