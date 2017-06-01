package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseStructureInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseStructureInfo record);

    int insertSelective(HouseStructureInfo record);

    int updateByWhereSelective(@Param("record") HouseStructureInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseStructureInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseStructureInfo record);

    int updateByPrimaryKey(HouseStructureInfo record);
}