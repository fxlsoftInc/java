package com.jialian.core.persistence.reader;

import java.util.List;

import com.jialian.api.domain.entity.PubDoc;
import com.jialian.api.domain.vo.PubDocVo;

public interface PubDocReaderMapper {
	
    PubDoc selectByPrimaryKey(Long id);

    List<PubDocVo> selectPubDoc();
    
    PubDocVo selectPubDocDetailed(Long id);
}