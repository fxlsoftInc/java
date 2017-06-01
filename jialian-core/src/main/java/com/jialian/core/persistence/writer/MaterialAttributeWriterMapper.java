package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MaterialAttributeWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialAttribute record);

    int insertSelective(MaterialAttribute record);

    int updateByWhereSelective(@Param("record") MaterialAttribute record, @Param("where") Where where);

    int updateByWhere(@Param("record") MaterialAttribute record, @Param("where") Where where);

    int updateByPrimaryKeySelective(MaterialAttribute record);

    int updateByPrimaryKey(MaterialAttribute record);
}