package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.MaterialPrice;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MaterialPriceWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialPrice record);

    int insertSelective(MaterialPrice record);

    int updateByWhereSelective(@Param("record") MaterialPrice record, @Param("where") Where where);

    int updateByWhere(@Param("record") MaterialPrice record, @Param("where") Where where);

    int updateByPrimaryKeySelective(MaterialPrice record);

    int updateByPrimaryKey(MaterialPrice record);
}