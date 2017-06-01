package com.jialian.core.service.impl.Decoration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Decoration.SignedRecordServiceApi;
import com.jialian.core.persistence.reader.SignedRecordReaderMapper;
import com.jialian.core.persistence.writer.SignedRecordWriterMapper;

@Service("signedRecordService")
public class SignedRecordServiceImpl implements SignedRecordServiceApi {
	
	@Resource
	private SignedRecordReaderMapper signedRecordReaderMapper;
	
	@Resource
	private SignedRecordWriterMapper signedRecordWriterMapper;
	
	public int countByWhere(Where where){
		return signedRecordReaderMapper.countByWhere(where);
	}

	public List<SignedRecord> selectByWhere(Where where){
		return signedRecordReaderMapper.selectByWhere(where);
	}

	public SignedRecord selectByPrimaryKey(Long id){
		return signedRecordReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return signedRecordWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return signedRecordWriterMapper.deleteByPrimaryKey(id);
	}

	public SignedRecord insert(SignedRecord record){
		signedRecordWriterMapper.insert(record);
		return record;
	}

	public SignedRecord insertSelective(SignedRecord record){
		signedRecordWriterMapper.insertSelective(record);
		return record;
	}

	public int updateByWhereSelective(SignedRecord record, Where where){
		return signedRecordWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(SignedRecord record, Where where){
		return signedRecordWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(SignedRecord record){
		return signedRecordWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SignedRecord record){
		return signedRecordWriterMapper.updateByPrimaryKey(record);
	}
}
