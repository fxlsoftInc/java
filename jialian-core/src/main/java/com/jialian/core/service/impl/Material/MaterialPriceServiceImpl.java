package com.jialian.core.service.impl.Material;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.MaterialPrice;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Material.MaterialPriceServiceApi;
import com.jialian.core.persistence.reader.MaterialPriceReaderMapper;
import com.jialian.core.persistence.writer.MaterialPriceWriterMapper;

@Service("materialPriceService")
public class MaterialPriceServiceImpl implements MaterialPriceServiceApi {

	@Resource
	private MaterialPriceReaderMapper materialPriceReaderMapper;
	
	@Resource
	private MaterialPriceWriterMapper materialPriceWriterMapper;
	
	public int countByWhere(Where where){
		return materialPriceReaderMapper.countByWhere(where);
	}

	public List<MaterialPrice> selectByWhere(Where where){
		return materialPriceReaderMapper.selectByWhere(where);
	}

	public MaterialPrice selectByPrimaryKey(Long id){
		return materialPriceReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return materialPriceWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return materialPriceWriterMapper.deleteByPrimaryKey(id);
	}

	public MaterialPrice insert(MaterialPrice record){
		int flag = materialPriceWriterMapper.insert(record);
		return flag > 0 ? record : null;
	}

	public MaterialPrice insertSelective(MaterialPrice record){
		int flag = materialPriceWriterMapper.insertSelective(record);
		return flag > 0 ? record : null;
	}

	public int updateByWhereSelective(MaterialPrice record, Where where){
		return materialPriceWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(MaterialPrice record, Where where){
		return materialPriceWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(MaterialPrice record){
		return materialPriceWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(MaterialPrice record){
		return materialPriceWriterMapper.updateByPrimaryKey(record);
	}
}
