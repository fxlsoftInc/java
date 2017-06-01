package com.jialian.core.service.impl.Decoration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.Memorando;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Decoration.MemorandoServiceApi;
import com.jialian.core.persistence.reader.MemorandoReaderMapper;
import com.jialian.core.persistence.writer.MemorandoWriterMapper;


@Service("memorandoService")
public class MemorandoServiceImpl implements MemorandoServiceApi {
	
	@Resource
	private MemorandoReaderMapper memorandoReaderMapper;
	
	@Resource
	private MemorandoWriterMapper memorandoWriterMapper;

	public int countByWhere(Where where){
		return memorandoReaderMapper.countByWhere(where);
	}

	public List<Memorando> selectByWhere(Where where){
		return memorandoReaderMapper.selectByWhere(where);
	}

	public Memorando selectByPrimaryKey(Long id){
		return memorandoReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return memorandoWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return memorandoWriterMapper.deleteByPrimaryKey(id);
	}

	public Memorando insert(Memorando record){
		memorandoWriterMapper.insert(record);
		return record;
	}

	public Memorando insertSelective(Memorando record){
		memorandoWriterMapper.insertSelective(record);
		return record;
	}

	public int updateByWhereSelective(Memorando record, Where where){
		return memorandoWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(Memorando record, Where where){
		return memorandoWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(Memorando record){
		return memorandoWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Memorando record){
		return memorandoWriterMapper.updateByPrimaryKey(record);
	}
}
