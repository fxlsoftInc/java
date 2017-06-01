package com.jialian.api.service;

import com.jialian.api.domain.SystemUser;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;

public interface TestServiceApi {
	
	ServiceResult<SystemUser> getSystemUser(int userId);
	
	ServiceResult<Page<SystemUser>> selectSystemUser(SystemUser systemUser,int currentPage,int onePageCount);

}