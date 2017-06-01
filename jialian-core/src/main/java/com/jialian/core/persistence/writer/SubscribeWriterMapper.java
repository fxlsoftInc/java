package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SubscribeWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    int updateByWhereSelective(@Param("record") Subscribe record, @Param("where") Where where);

    int updateByWhere(@Param("record") Subscribe record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
    
    int insertSelectiveBackId(Subscribe record);
}