package com.jialian.core.service.impl.House;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.vo.HouseStyleVo;
import com.jialian.api.service.House.HouseStyleServiceApi;
import com.jialian.core.persistence.reader.HouseStyleReaderMapper;

@Service("houseStyleService")
public class HouseStyleServiceImpl implements HouseStyleServiceApi {

	@Resource
	HouseStyleReaderMapper houseStyleReaderMapper;
	
	@Override
	public ServiceResult<List<HouseStyleVo>> getHouseStyle() {
		ServiceResult<List<HouseStyleVo>> result=new ServiceResult<List<HouseStyleVo>>();
		List<HouseStyleVo> list=houseStyleReaderMapper.selectHouseStyleAll();
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(list);
		return result;
	}

}
