package com.jialian.core.service.impl.Address;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.service.Address.AddressServiceApi;
import com.jialian.core.persistence.writer.AddressWriterMapper;

@Service("addressService")
public class AddressServiceImpl implements AddressServiceApi {
	
	@Resource
	AddressWriterMapper adressWriterMapper;

	@Override
	public ServiceResult<Object> updateAddress(Address address) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=adressWriterMapper.updateByPrimaryKeySelective(address);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}
		return result;
	}

	@Override
	public Address insertAddress(Address address) {
		int result = adressWriterMapper.insertSelective(address);
		return result > 0 ? address:null;
	}

	@Override
	public int updateByPrimaryKeySelective(Address record) {
		return adressWriterMapper.updateByPrimaryKeySelective(record);
	}

}
