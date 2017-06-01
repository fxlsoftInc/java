package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.MainMaterialOrder;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MainMaterialOrderWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(MainMaterialOrder record);

    int insertSelective(MainMaterialOrder record);

    int updateByWhereSelective(@Param("record") MainMaterialOrder record, @Param("where") Where where);

    int updateByWhere(@Param("record") MainMaterialOrder record, @Param("where") Where where);

    int updateByPrimaryKeySelective(MainMaterialOrder record);

    int updateByPrimaryKey(MainMaterialOrder record);
}