package com.jialian.api.service.Order;

import java.util.List;

import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.Where;

public interface PayRecordServiceApi {
	
	int countByWhere(Where where);

    List<PayRecord> selectByWhere(Where where);

    PayRecord selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    PayRecord insert(PayRecord record);

    PayRecord insertSelective(PayRecord record);

    int updateByWhereSelective(PayRecord record,  Where where);

    int updateByWhere(PayRecord record,  Where where);

    int updateByPrimaryKeySelective(PayRecord record);

    int updateByPrimaryKey(PayRecord record);
    
    Double sumPayAmtByWhere(Where where);
}
