package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.PubDoc;

public interface PubDocWriterMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(PubDoc record);

    int insertSelective(PubDoc record);

    int updateByPrimaryKeySelective(PubDoc record);

    int updateByPrimaryKey(PubDoc record);
}