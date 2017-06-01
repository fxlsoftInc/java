package com.jialian.core.service.impl.Combo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Combo.ComboInfoServiceApi;
import com.jialian.core.persistence.reader.ComboInfoReaderMapper;
import com.jialian.core.persistence.writer.ComboInfoWriterMapper;

@Service("comboInfoService")
public class ComboInfoServiceImpl implements ComboInfoServiceApi{

	@Resource
	private ComboInfoReaderMapper comboInfoReaderMapper;
	
	@Resource
	private ComboInfoWriterMapper comboInfoWriterMapper;
	
	@Override
	public int countByWhere(Where where) {
		return comboInfoReaderMapper.countByWhere(where);
	}

	@Override
	public List<ComboInfo> selectByWhere(Where where) {
		return comboInfoReaderMapper.selectByWhere(where);
	}

	@Override
	public ComboInfo selectByPrimaryKey(Long id) {
		return comboInfoReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByWhere(Where where) {
		return comboInfoWriterMapper.deleteByWhere(where);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return comboInfoWriterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ComboInfo record) {
		return comboInfoWriterMapper.insert(record);
	}

	@Override
	public int insertSelective(ComboInfo record) {
		return comboInfoWriterMapper.insertSelective(record);
	}

	@Override
	public int updateByWhereSelective(ComboInfo record, Where where) {
		return comboInfoWriterMapper.updateByWhereSelective(record, where);
	}

	@Override
	public int updateByWhere(ComboInfo record, Where where) {
		return comboInfoWriterMapper.updateByWhere(record, where);
	}

	@Override
	public int updateByPrimaryKeySelective(ComboInfo record) {
		return comboInfoWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ComboInfo record) {
		return comboInfoWriterMapper.updateByPrimaryKey(record);
	}

}
