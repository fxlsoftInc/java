package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface OrderTrackWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(OrderTrack record);

    int insertSelective(OrderTrack record);

    int updateByWhereSelective(@Param("record") OrderTrack record, @Param("where") Where where);

    int updateByWhere(@Param("record") OrderTrack record, @Param("where") Where where);

    int updateByPrimaryKeySelective(OrderTrack record);

    int updateByPrimaryKey(OrderTrack record);
}