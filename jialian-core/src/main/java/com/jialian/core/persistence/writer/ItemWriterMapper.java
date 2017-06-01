package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface ItemWriterMapper {
	
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    int updateByWhereSelective(@Param("record") Item record, @Param("where") Where where);

    int updateByWhere(@Param("record") Item record, @Param("where") Where where);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
    
}