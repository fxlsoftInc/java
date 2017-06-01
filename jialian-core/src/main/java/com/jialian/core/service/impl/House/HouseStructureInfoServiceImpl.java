package com.jialian.core.service.impl.House;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.House.HouseStructureInfoServiceApi;
import com.jialian.core.persistence.reader.HouseStructureInfoReaderMapper;
import com.jialian.core.persistence.writer.HouseStructureInfoWriterMapper;

@Service("houseStructureInfoService")
public class HouseStructureInfoServiceImpl implements HouseStructureInfoServiceApi {

	@Resource
	HouseStructureInfoWriterMapper houseStructureInfoWriterMapper;
	
	@Resource
	HouseStructureInfoReaderMapper houseStructureInfoReaderMapper;
	
	@Override
	public ServiceResult<Object> updateHouseStructureInfo(HouseStructureInfo houseStructureInfo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseStructureInfoWriterMapper.updateByPrimaryKeySelective(houseStructureInfo);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addHouseStructureInfo(HouseStructureInfo houseStructureInfo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseStructureInfoWriterMapper.insertSelective(houseStructureInfo);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
		}
		return result;
	}

	@Override
	public int countByWhere(Where where) {
		return houseStructureInfoReaderMapper.countByWhere(where);
	}

	@Override
	public List<HouseStructureInfo> selectByWhere(Where where) {
		return houseStructureInfoReaderMapper.selectByWhere(where);
	}

	@Override
	public HouseStructureInfo selectByPrimaryKey(Long id) {
		return houseStructureInfoReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByWhereSelective(HouseStructureInfo record, Where where) {
		return houseStructureInfoWriterMapper.updateByWhereSelective(record, where);
	}

	@Override
	public int updateByWhere(HouseStructureInfo record, Where where) {
		return houseStructureInfoWriterMapper.updateByWhere(record, where);
	}

	@Override
	public int updateByPrimaryKeySelective(HouseStructureInfo record) {
		return houseStructureInfoWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HouseStructureInfo record) {
		return houseStructureInfoWriterMapper.updateByPrimaryKey(record);
	}

}
