package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Collect;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface CollectWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Collect record);

    int insertSelective(Collect record);

    int updateByWhereSelective(@Param("record") Collect record, @Param("where") Where where);

    int updateByWhere(@Param("record") Collect record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}