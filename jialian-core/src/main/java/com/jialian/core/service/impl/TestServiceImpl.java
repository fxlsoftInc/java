package com.jialian.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.SystemUser;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.service.TestServiceApi;
import com.jialian.core.persistence.reader.SystemUserReaderMapper;

@Service("testService")
public class TestServiceImpl implements TestServiceApi {
	
	@Resource
	private SystemUserReaderMapper systemUserReaderMapper;
	

	@Override
	public ServiceResult<SystemUser> getSystemUser(int userId) {
		ServiceResult<SystemUser> serviceResult =  new ServiceResult<>();
		serviceResult.setData(systemUserReaderMapper.selectByPrimaryKey(userId));
		serviceResult.setOk(true);
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Page<SystemUser>> selectSystemUser(SystemUser systemUser, int currentPage, int onePageCount) {
		ServiceResult<Page<SystemUser>> serviceResult = new ServiceResult<Page<SystemUser>>();
		int countRecord = systemUserReaderMapper.selectSystemUserCount(systemUser);
		Page<SystemUser> page = new Page<SystemUser>(currentPage,countRecord,onePageCount);
		if(countRecord>0){
			List<SystemUser> list = systemUserReaderMapper.selectSystemUser(systemUser,page.getStartIndex(),page.getOnePageCount());
			page.setList(list);
			serviceResult.setOk(true);
		}
		serviceResult.setData(page);
		return serviceResult;
	}
	
}