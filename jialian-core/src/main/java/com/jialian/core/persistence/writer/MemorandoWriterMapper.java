package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Memorando;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MemorandoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Memorando record);

    int insertSelective(Memorando record);

    int updateByWhereSelective(@Param("record") Memorando record, @Param("where") Where where);

    int updateByWhere(@Param("record") Memorando record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Memorando record);

    int updateByPrimaryKey(Memorando record);
}