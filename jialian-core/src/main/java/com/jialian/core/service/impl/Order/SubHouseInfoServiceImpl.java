package com.jialian.core.service.impl.Order;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.SubHouseInfo;
import com.jialian.api.service.Order.SubHouseInfoServiceApi;
import com.jialian.core.persistence.writer.SubHouseInfoWriterMapper;


@Service("subHouseInfoService")
public class SubHouseInfoServiceImpl implements SubHouseInfoServiceApi {

	@Resource
	private SubHouseInfoWriterMapper subHouseInfoWriterMapper;
	
	@Override
	public int updateByPrimaryKeySelective(SubHouseInfo record) {
		return subHouseInfoWriterMapper.updateByPrimaryKeySelective(record);
	}

}
