package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.OrderTrackVO;

import java.util.List;

public interface OrderTrackReaderMapper {
    int countByWhere(Where where);

    List<OrderTrack> selectByWhere(Where where);

    OrderTrack selectByPrimaryKey(Long id);
    
    OrderTrackVO selectTrackOrderAndPayRecord(Long id);
    
    OrderTrackVO selectTrackOrderAndSemDecOrder(Long id);
}