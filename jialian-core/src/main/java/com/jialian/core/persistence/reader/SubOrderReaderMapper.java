package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.OrderQuery;
import com.jialian.api.domain.vo.SubLookVo;
import com.jialian.api.domain.vo.SubOrderInfoVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SubOrderReaderMapper {
    int countByWhere(Where where);

    List<SubOrder> selectByWhere(Where where);

    SubOrder selectByPrimaryKey(Long id);
    
    SubOrderInfoVO selectByOrderNo(String orderNo);
    
    int selectSubLookOrderCount(@Param("query")OrderQuery query);
    
    List<SubLookVo> selectSubLookOrderList(@Param("query")OrderQuery query);
}