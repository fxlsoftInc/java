package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SignedRecordWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SignedRecord record);

    int insertSelective(SignedRecord record);

    int updateByWhereSelective(@Param("record") SignedRecord record, @Param("where") Where where);

    int updateByWhere(@Param("record") SignedRecord record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SignedRecord record);

    int updateByPrimaryKey(SignedRecord record);
}