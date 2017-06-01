package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SignedOrderWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SignedOrder record);

    int insertSelective(SignedOrder record);

    int updateByWhereSelective(@Param("record") SignedOrder record, @Param("where") Where where);

    int updateByWhere(@Param("record") SignedOrder record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SignedOrder record);

    int updateByPrimaryKey(SignedOrder record);
}