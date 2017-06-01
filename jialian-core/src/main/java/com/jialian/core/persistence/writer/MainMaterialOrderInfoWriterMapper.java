package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.MainMaterialOrderInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface MainMaterialOrderInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(MainMaterialOrderInfo record);

    int insertSelective(MainMaterialOrderInfo record);

    int updateByWhereSelective(@Param("record") MainMaterialOrderInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") MainMaterialOrderInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(MainMaterialOrderInfo record);

    int updateByPrimaryKey(MainMaterialOrderInfo record);
}