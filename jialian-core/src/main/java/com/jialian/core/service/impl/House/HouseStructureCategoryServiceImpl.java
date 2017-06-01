package com.jialian.core.service.impl.House;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureCategory;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.House.HouseStructureCategoryServiceApi;
import com.jialian.core.persistence.reader.HouseStructureCategoryReaderMapper;
import com.jialian.core.persistence.writer.HouseStructureCategoryWriterMapper;

@Service("houseStructureCategoryService")
public class HouseStructureCategoryServiceImpl implements HouseStructureCategoryServiceApi {

	@Resource
	HouseStructureCategoryWriterMapper houseStructureCategoryWriterMapper;
	
	@Resource
	HouseStructureCategoryReaderMapper houseStructureCategoryReaderMapper;
	
	@Override
	public ServiceResult<Object> updateHouseStructureCategory(HouseStructureCategory category) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseStructureCategoryWriterMapper.updateByPrimaryKeySelective(category);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addHouseStructureCategory(HouseStructureCategory category) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseStructureCategoryWriterMapper.insertSelective(category);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
		}
		return result;
	}

	@Override
	public int countByWhere(Where where) {
		return houseStructureCategoryReaderMapper.countByWhere(where);
	}

	@Override
	public List<HouseStructureCategory> selectByWhere(Where where) {
		return houseStructureCategoryReaderMapper.selectByWhere(where);
	}

	@Override
	public HouseStructureCategory selectByPrimaryKey(Long id) {
		return houseStructureCategoryReaderMapper.selectByPrimaryKey(id);
	}

}
