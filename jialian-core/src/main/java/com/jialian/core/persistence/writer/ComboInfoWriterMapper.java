package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface ComboInfoWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(ComboInfo record);

    int insertSelective(ComboInfo record);

    int updateByWhereSelective(@Param("record") ComboInfo record, @Param("where") Where where);

    int updateByWhere(@Param("record") ComboInfo record, @Param("where") Where where);

    int updateByPrimaryKeySelective(ComboInfo record);

    int updateByPrimaryKey(ComboInfo record);
}