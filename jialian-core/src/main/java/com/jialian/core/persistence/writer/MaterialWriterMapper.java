package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MaterialWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Material record);

    int insertSelective(Material record);

    int updateByWhereSelective(@Param("record") Material record, @Param("where") Where where);

    int updateByWhereWithBLOBs(@Param("record") Material record, @Param("where") Where where);

    int updateByWhere(@Param("record") Material record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKeyWithBLOBs(Material record);

    int updateByPrimaryKey(Material record);
}