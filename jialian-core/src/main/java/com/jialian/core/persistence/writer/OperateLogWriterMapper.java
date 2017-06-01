package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.OperateLog;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface OperateLogWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    int updateByWhereSelective(@Param("record") OperateLog record, @Param("where") Where where);

    int updateByWhere(@Param("record") OperateLog record, @Param("where") Where where);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);
}