package com.jialian.core.service.impl.Material;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Material.MaterialServiceApi;
import com.jialian.core.persistence.reader.MaterialReaderMapper;
import com.jialian.core.persistence.writer.MaterialWriterMapper;

@Service("materialService")
public class MaterialServiceImpl implements MaterialServiceApi{

	@Resource
	private MaterialReaderMapper materialReaderMapper;
	
	@Resource
	private MaterialWriterMapper materialWriterMapper;
	
	public int countByWhere(Where where){
		return materialReaderMapper.countByWhere(where);
	}

	public List<Material> selectByWhereWithBLOBs(Where where){
		return materialReaderMapper.selectByWhereWithBLOBs(where);
	}

	public List<Material> selectByWhere(Where where){
		return materialReaderMapper.selectByWhere(where);
	}

	public Material selectByPrimaryKey(Long id){
		return materialReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return materialWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return materialWriterMapper.deleteByPrimaryKey(id);
	}

	public Material insert(Material record){
		int flag = materialWriterMapper.insert(record);
		return flag > 0 ? record : null;
	}

	public Material insertSelective(Material record){
		int flag = materialWriterMapper.insertSelective(record);
		return flag > 0 ? record : null;
	}

	public int updateByWhereSelective(Material record, Where where){
		return materialWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhereWithBLOBs(Material record, Where where){
		return materialWriterMapper.updateByWhereWithBLOBs(record, where);
	}

	public int updateByWhere(Material record, Where where){
		return materialWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(Material record){
		return materialWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Material record){
		return materialWriterMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Material record){
		return materialWriterMapper.updateByPrimaryKey(record);
	}
}
