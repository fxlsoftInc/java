package com.jialian.core.service.impl.House;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.service.House.HouseInfoServiceApi;
import com.jialian.core.persistence.writer.HouseInfoWriterMapper;

@Service("houseInfoService")
public class HouseInfoServiceImpl implements HouseInfoServiceApi {
	
	@Resource
	HouseInfoWriterMapper houseInfoWriterMapper;

	@Override
	public ServiceResult<Object> updateHouseInfo(HouseInfo houseInfo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseInfoWriterMapper.updateByPrimaryKeySelective(houseInfo);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addHouseInfo(HouseInfo houseInfo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=houseInfoWriterMapper.insertSelective(houseInfo);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
		}
		return result;
	}

}
