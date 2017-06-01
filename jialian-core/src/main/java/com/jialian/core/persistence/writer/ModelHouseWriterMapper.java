package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ModelHouse;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface ModelHouseWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(ModelHouse record);

    int insertSelective(ModelHouse record);

    int updateByWhereSelective(@Param("record") ModelHouse record, @Param("where") Where where);

    int updateByWhere(@Param("record") ModelHouse record, @Param("where") Where where);

    int updateByPrimaryKeySelective(ModelHouse record);

    int updateByPrimaryKey(ModelHouse record);
}