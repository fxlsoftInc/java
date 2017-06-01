package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseStructureCategory;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseStructureCategoryWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseStructureCategory record);

    int insertSelective(HouseStructureCategory record);

    int updateByWhereSelective(@Param("record") HouseStructureCategory record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseStructureCategory record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseStructureCategory record);

    int updateByPrimaryKey(HouseStructureCategory record);
}