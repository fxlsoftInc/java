package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.CaseHouseInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(CaseHouseInfo record);

    int insertSelective(CaseHouseInfo record);

    int updateByWhereSelective(@Param("record") CaseHouseInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") CaseHouseInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(CaseHouseInfo record);

    int updateByPrimaryKey(CaseHouseInfo record);
}