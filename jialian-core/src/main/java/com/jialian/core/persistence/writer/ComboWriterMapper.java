package com.jialian.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.Where;

public interface ComboWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Combo record);

    int insertSelective(Combo record);

    int updateByWhereSelective(@Param("record") Combo record, @Param("where") Where where);

    int updateByWhere(@Param("record") Combo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Combo record);

    int updateByPrimaryKey(Combo record);
}