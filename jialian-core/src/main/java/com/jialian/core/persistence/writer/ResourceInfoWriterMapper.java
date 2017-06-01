package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface ResourceInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(ResourceInfo record);

    int insertSelective(ResourceInfo record);

    int updateByWhereSelective(@Param("record") ResourceInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") ResourceInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(ResourceInfo record);

    int updateByPrimaryKey(ResourceInfo record);
}