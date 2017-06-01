package com.jialian.core.service.impl.House;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.House.HouseTypeServiceApi;
import com.jialian.core.persistence.reader.HouseTypeReaderMapper;
import com.jialian.core.persistence.writer.HouseTypeWriterMapper;

@Service("houseTypeService")
public class HouseTypeServiceImpl implements HouseTypeServiceApi{
	
	@Resource
	private HouseTypeReaderMapper houseTypeReaderMapper;
	
	@Resource
	private HouseTypeWriterMapper houseTypeWriterMapper;
	
	public int countByWhere(Where where){
		return houseTypeReaderMapper.countByWhere(where);
	}

	public List<HouseType> selectByWhere(Where where){
    	return houseTypeReaderMapper.selectByWhere(where);
    }

	public HouseType selectByPrimaryKey(Long id){
    	return houseTypeReaderMapper.selectByPrimaryKey(id);
    }
    
	public List<HouseType> selectByHouseTypeNo(String houseTypeNo){
    	return houseTypeReaderMapper.selectByHouseTypeNo(houseTypeNo);
    }
	
	public int deleteByWhere(Where where){
		return houseTypeWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
    	return houseTypeWriterMapper.deleteByPrimaryKey(id);
    }

	public HouseType insert(HouseType record){
    	houseTypeWriterMapper.insert(record);
    	return record;
    }

	public HouseType insertSelective(HouseType record){
    	houseTypeWriterMapper.insertSelective(record);
    	return record;
    }

	public int updateByWhereSelective(HouseType record, Where where){
    	return houseTypeWriterMapper.updateByWhereSelective(record, where);
    }

	public int updateByWhere(HouseType record, Where where){
    	return houseTypeWriterMapper.updateByWhere(record, where);
    }

	public int updateByPrimaryKeySelective(HouseType record){
    	return houseTypeWriterMapper.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(HouseType record){
    	return houseTypeWriterMapper.updateByPrimaryKey(record);
    }
}
