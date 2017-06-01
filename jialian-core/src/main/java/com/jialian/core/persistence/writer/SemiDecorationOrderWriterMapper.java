package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SemiDecorationOrder;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SemiDecorationOrderWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SemiDecorationOrder record);

    int insertSelective(SemiDecorationOrder record);

    int updateByWhereSelective(@Param("record") SemiDecorationOrder record, @Param("where") Where where);

    int updateByWhere(@Param("record") SemiDecorationOrder record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SemiDecorationOrder record);

    int updateByPrimaryKey(SemiDecorationOrder record);
}