package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SubHouseInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SubHouseInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SubHouseInfo record);

    int insertSelective(SubHouseInfo record);
    
    int insertSelectiveBackId(SubHouseInfo record);

    int updateByWhereSelective(@Param("record") SubHouseInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") SubHouseInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SubHouseInfo record);

    int updateByPrimaryKey(SubHouseInfo record);
}