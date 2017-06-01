package com.jialian.api.service.Decoration;

import java.util.List;

import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.Where;

public interface SignedRecordServiceApi {
	
	int countByWhere(Where where);

    List<SignedRecord> selectByWhere(Where where);

    SignedRecord selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    SignedRecord insert(SignedRecord record);

    SignedRecord insertSelective(SignedRecord record);

    int updateByWhereSelective(SignedRecord record, Where where);

    int updateByWhere(SignedRecord record, Where where);

    int updateByPrimaryKeySelective(SignedRecord record);

    int updateByPrimaryKey(SignedRecord record);
}
