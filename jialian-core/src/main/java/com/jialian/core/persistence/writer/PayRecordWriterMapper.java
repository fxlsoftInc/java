package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface PayRecordWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(PayRecord record);

    int insertSelective(PayRecord record);

    int updateByWhereSelective(@Param("record") PayRecord record, @Param("where") Where where);

    int updateByWhere(@Param("record") PayRecord record, @Param("where") Where where);

    int updateByPrimaryKeySelective(PayRecord record);

    int updateByPrimaryKey(PayRecord record);
}