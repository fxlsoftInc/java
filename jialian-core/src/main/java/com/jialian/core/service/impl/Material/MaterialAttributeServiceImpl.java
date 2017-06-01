package com.jialian.core.service.impl.Material;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Material.MaterialAttributeServiceApi;
import com.jialian.core.persistence.reader.MaterialAttributeReaderMapper;
import com.jialian.core.persistence.writer.MaterialAttributeWriterMapper;

@Service("materialAttributeService")
public class MaterialAttributeServiceImpl implements  MaterialAttributeServiceApi{

	@Resource
	private MaterialAttributeReaderMapper materialAttributeReaderMapper;
	@Resource
	private MaterialAttributeWriterMapper materialAttributeWriterMapper;
	
	public int countByWhere(Where where){
		return materialAttributeReaderMapper.countByWhere(where);
	}

	public List<MaterialAttribute> selectByWhere(Where where){
		return materialAttributeReaderMapper.selectByWhere(where);
	}

	public MaterialAttribute selectByPrimaryKey(Long id){
		return materialAttributeReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return materialAttributeWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return materialAttributeWriterMapper.deleteByPrimaryKey(id);
	}

	public MaterialAttribute insert(MaterialAttribute record){
		int flag = materialAttributeWriterMapper.insert(record);
		if(flag > 0)return record;
		return null;
	}

	public MaterialAttribute insertSelective(MaterialAttribute record){
		int flag = materialAttributeWriterMapper.insertSelective(record);
		if(flag > 0) return record;
		return null;
	}

	public int updateByWhereSelective(MaterialAttribute record, Where where){
		return materialAttributeWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(MaterialAttribute record, Where where){
		return materialAttributeWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(MaterialAttribute record){
		return materialAttributeWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(MaterialAttribute record){
		return materialAttributeWriterMapper.updateByPrimaryKey(record);
	}

	/* （非 Javadoc）
	 * @see com.jialian.api.service.Material.MaterialAttributeServiceApi#selectShopProdByPrimaryKey(long)
	 */
	public MaterialAttribute selectShopProdByPrimaryKey(Long ptid) {
		return materialAttributeReaderMapper.selectShopProdByPrimaryKey(ptid);
	}
}
