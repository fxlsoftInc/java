package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseStyle;
import com.jialian.api.domain.entity.HouseStyleWithBLOBs;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseStyleWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseStyleWithBLOBs record);

    int insertSelective(HouseStyleWithBLOBs record);

    int updateByWhereSelective(@Param("record") HouseStyleWithBLOBs record, @Param("where") Where where);

    int updateByWhereWithBLOBs(@Param("record") HouseStyleWithBLOBs record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseStyle record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseStyleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HouseStyleWithBLOBs record);

    int updateByPrimaryKey(HouseStyle record);
}