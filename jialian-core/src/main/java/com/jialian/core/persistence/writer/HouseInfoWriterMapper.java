package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseInfo record);

    int insertSelective(HouseInfo record);
    
    int insertSelectiveBackId(HouseInfo record);

    int updateByWhereSelective(@Param("record") HouseInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseInfo record);

    int updateByPrimaryKey(HouseInfo record);
}