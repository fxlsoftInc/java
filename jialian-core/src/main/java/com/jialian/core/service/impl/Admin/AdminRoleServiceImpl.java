package com.jialian.core.service.impl.Admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.query.RoleQuery;
import com.jialian.api.service.Admin.AdminRoleServiceApi;
import com.jialian.core.persistence.reader.AdminRoleReaderMapper;
import com.jialian.core.persistence.writer.AdminRoleWriterMapper;

@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleServiceApi {
	
	@Resource
	AdminRoleReaderMapper adminRoleReaderMapper;
	
	@Resource
	AdminRoleWriterMapper adminRoleWriterMapper;

	@Override
	public ServiceResult<Page<AdminRole>> getRoleList(RoleQuery query) {
		ServiceResult<Page<AdminRole>> result=new ServiceResult<Page<AdminRole>>();
		int countRecord=adminRoleReaderMapper.selectAdminRoleCountByQuery(query);
		Page<AdminRole> page=new Page<AdminRole>(query.getCurrentPage(),countRecord,query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<AdminRole> list=adminRoleReaderMapper.selectAdminRoleListByQuery(query);
			page.setList(list);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setOk(true);
		result.setData(page);
		return result;
	}
	
	@Override
	public ServiceResult<List<AdminRole>> getAdminRole(RoleQuery query) {
		ServiceResult<List<AdminRole>> result=new ServiceResult<List<AdminRole>>();
		List<AdminRole> list=adminRoleReaderMapper.selectAdminRoleList(query);
		result.setData(list);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updateRole(AdminRole role) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=adminRoleWriterMapper.updateByPrimaryKeySelective(role);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}else{
			result.setComment("修改失败");
		}
		return result;
	}

}
