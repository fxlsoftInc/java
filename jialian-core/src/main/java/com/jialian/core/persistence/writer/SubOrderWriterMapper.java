package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface SubOrderWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(SubOrder record);

    int insertSelective(SubOrder record);

    int updateByWhereSelective(@Param("record") SubOrder record, @Param("where") Where where);

    int updateByWhere(@Param("record") SubOrder record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SubOrder record);

    int updateByPrimaryKey(SubOrder record);
}