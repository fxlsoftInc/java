package com.jialian.core.service.impl.Admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminRule;
import com.jialian.api.domain.query.AdminPermissionQuery;
import com.jialian.api.service.Admin.AdminPermissionServiceApi;
import com.jialian.core.persistence.reader.AdminPermissionReaderMapper;
import com.jialian.core.persistence.writer.AdminPermissionWriterMapper;
import com.jialian.core.persistence.writer.AdminRuleWriterMapper;

@Service("adminPermissionService")
public class AdminPermissionServiceImpl implements AdminPermissionServiceApi {
	
	@Resource
	AdminPermissionReaderMapper adminPermissionReaderMapper;
	
	@Resource
	AdminPermissionWriterMapper adminPermissionWriterMapper;
	
	@Resource
	AdminRuleWriterMapper adminRuleWriterMapper;

	@Override
	public ServiceResult<List<AdminPermission>> getAdminPermissionByRoleId(Long roleId) {
		ServiceResult<List<AdminPermission>> result=new ServiceResult<List<AdminPermission>>();
		List<AdminPermission> list=adminPermissionReaderMapper.selectAdminPermissionByRoleId(roleId);
		result.setData(list);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		return result;
	}
	
	@Override
	public ServiceResult<List<AdminPermission>> getAdminPermissionList(AdminPermissionQuery query) {
		ServiceResult<List<AdminPermission>> result=new ServiceResult<List<AdminPermission>>();
		List<AdminPermission> list=adminPermissionReaderMapper.selectAdminPermissionList(query);
		result.setData(list);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updateAdminPermission(Long roleId,Long[] permissionIds) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		//清除旧数据
		adminRuleWriterMapper.deleteByRoleId(roleId);
		for(int i=0;i<permissionIds.length;i++){
			AdminRule adminRule=new AdminRule();
			adminRule.setRoleId(roleId);
			adminRule.setPermissionId(permissionIds[i]);
			adminRuleWriterMapper.insertSelective(adminRule);
		}
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setComment("修改成功");
		return result;
	}
}
