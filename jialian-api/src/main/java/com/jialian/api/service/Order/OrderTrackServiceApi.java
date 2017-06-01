package com.jialian.api.service.Order;

import java.util.List;

import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.OrderTrackVO;

public interface OrderTrackServiceApi {
	
	int countByWhere(Where where);

    List<OrderTrack> selectByWhere(Where where);

    OrderTrack selectByPrimaryKey(Long id);
    
    OrderTrackVO selectTrackOrderAndPayRecord(Long id);
    
    OrderTrackVO selectTrackOrderAndSemDecOrder(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    OrderTrack insert(OrderTrack record);

    OrderTrack insertSelective(OrderTrack record);

    int updateByWhereSelective(OrderTrack record, Where where);

    int updateByWhere(OrderTrack record, Where where);

    int updateByPrimaryKeySelective(OrderTrack record);

    int updateByPrimaryKey(OrderTrack record);
}
