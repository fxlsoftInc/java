package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.LaborCost;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface LaborCostWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(LaborCost record);

    int insertSelective(LaborCost record);

    int updateByWhereSelective(@Param("record") LaborCost record, @Param("where") Where where);

    int updateByWhere(@Param("record") LaborCost record, @Param("where") Where where);

    int updateByPrimaryKeySelective(LaborCost record);

    int updateByPrimaryKey(LaborCost record);
}