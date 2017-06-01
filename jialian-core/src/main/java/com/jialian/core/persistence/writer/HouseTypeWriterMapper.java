package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseTypeWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseType record);

    int insertSelective(HouseType record);

    int updateByWhereSelective(@Param("record") HouseType record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseType record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseType record);

    int updateByPrimaryKey(HouseType record);
}