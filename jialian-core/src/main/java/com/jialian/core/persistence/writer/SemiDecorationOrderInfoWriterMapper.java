package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SemiDecorationOrderInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SemiDecorationOrderInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SemiDecorationOrderInfo record);

    int insertSelective(SemiDecorationOrderInfo record);

    int updateByWhereSelective(@Param("record") SemiDecorationOrderInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") SemiDecorationOrderInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SemiDecorationOrderInfo record);

    int updateByPrimaryKey(SemiDecorationOrderInfo record);
}