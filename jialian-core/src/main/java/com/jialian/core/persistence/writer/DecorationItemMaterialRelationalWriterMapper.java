package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.DecorationItemMaterialRelational;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface DecorationItemMaterialRelationalWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(DecorationItemMaterialRelational record);

    int insertSelective(DecorationItemMaterialRelational record);

    int updateByWhereSelective(@Param("record") DecorationItemMaterialRelational record, @Param("where") Where where);

    int updateByWhere(@Param("record") DecorationItemMaterialRelational record, @Param("where") Where where);

    int updateByPrimaryKeySelective(DecorationItemMaterialRelational record);

    int updateByPrimaryKey(DecorationItemMaterialRelational record);
}