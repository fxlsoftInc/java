package com.jialian.core.service.impl.Admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.AdminRoleQuery;
import com.jialian.api.domain.query.AdminUserQuery;
import com.jialian.api.domain.vo.AdminUserVo;
import com.jialian.api.service.Admin.AdminUserServiceApi;
import com.jialian.core.persistence.reader.AdminPermissionReaderMapper;
import com.jialian.core.persistence.reader.AdminRoleReaderMapper;
import com.jialian.core.persistence.reader.AdminUserReaderMapper;
import com.jialian.core.persistence.writer.AdminRoleWriterMapper;
import com.jialian.core.persistence.writer.AdminUserWriterMapper;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserServiceApi{

	@Resource
	private AdminUserReaderMapper adminUserReaderMapper;
	
	@Resource
	private AdminUserWriterMapper adminUserWriterMapper;
	
	@Resource
	private AdminPermissionReaderMapper adminPermissionReaderMapper;
	
	@Resource
	private AdminRoleReaderMapper adminRoleReaderMapper;
	
	@Resource
	private AdminRoleWriterMapper adminRoleWriterMapper;
	
	@Override
	public int countByWhere(Where where) {
		return adminUserReaderMapper.countByWhere(where);
	}

	@Override
	public List<AdminUser> selectByWhere(Where where) {
		return adminUserReaderMapper.selectByWhere(where);
	}

	@Override
	public AdminUser selectByPrimaryKey(Long id) {
		return adminUserReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByWhere(Where where) {
		return adminUserWriterMapper.deleteByWhere(where);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return adminUserWriterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AdminUser record) {
		return adminUserWriterMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminUser record) {
		return adminUserWriterMapper.insertSelective(record);
	}

	@Override
	public int updateByWhereSelective(AdminUser record, Where where) {
		return adminUserWriterMapper.updateByWhereSelective(record, where);
	}

	@Override
	public int updateByWhere(AdminUser record, Where where) {
		return adminUserWriterMapper.updateByWhere(record, where);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminUser record) {
		return adminUserWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminUser record) {
		return adminUserWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AdminPermission> selectPermissionByUserId(Long userId) {
		return adminPermissionReaderMapper.selectByUserId(userId);
	}

	/**
	 * 删除管理员
	 */
	@Override
	public int deleteAdminUserById(Long id) {
		int result = 0;
		AdminUser adminUser = adminUserReaderMapper.selectByPrimaryKey(id);
		if(adminUser == null || adminUser.equals("")){
			return result;
		}else{
			adminUser.setAdminStatus((short)0);
			adminUser.setUpdateTime(new Date());
			result = adminUserWriterMapper.updateByPrimaryKeySelective(adminUser);
			return result;
		}
	}

	/**
	 * 添加账户
	 */
	@Override
	public int addAdminUser(AdminUser adminUser, AdminRole adminRole) {
		int result = 0;
		result = adminUserWriterMapper.insertSelective(adminUser);
		if(result == 1){
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("telephone", adminUser.getTelephone());
			List<AdminUser> adminUserList = adminUserReaderMapper.selectByWhere(where);
			if(adminUserList == null || adminUserList.equals("")){
				result = 0;
			}else{
				adminUser = adminUserList.get(0);
				adminRole.setUserId(adminUser.getId());
				adminRole.setParentId(0L);//默认为0
				adminRole.setSortOrder(0);//默认为0
				adminRole.setTreeLevel(0);//默认为0
				result = adminRoleWriterMapper.insertSelective(adminRole);
			}
		}
		return  result;
	}
	

	@Override
	public ServiceResult<Object> addAdminUser_new(AdminUser adminUser) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=adminUserWriterMapper.insertSelective(adminUser);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
		}else{
			result.setComment("添加失败");
		}
		return result;
	}


	/**
	 * 后台--系统设置》管理账户
	 */
	@Override
	public ServiceResult<List<AdminUserVo>> selectAdminRoleList(
			AdminRoleQuery query) {
		ServiceResult<List<AdminUserVo>> result = new ServiceResult<List<AdminUserVo>>();
		List<AdminUserVo> adminUserList = adminUserReaderMapper.selectAdminRoleList(query);
		result.setData(adminUserList);
		result.setOk(true);
		return result;
	}

	@Override
	public ServiceResult<Page<AdminUserVo>> getAdminUserListByQuery(AdminUserQuery query) {
		ServiceResult<Page<AdminUserVo>> result=new ServiceResult<Page<AdminUserVo>>();
		int countRecord=adminUserReaderMapper.selectAdminUserCountByQuery(query);
		Page<AdminUserVo> page=new Page<AdminUserVo>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<AdminUserVo> list= adminUserReaderMapper.selectAdminUserListByQuery(query);
			page.setList(list);
		}
		result.setData(page);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<AdminUserVo> getAdminUserById(Long id) {
		ServiceResult<AdminUserVo> result=new ServiceResult<AdminUserVo>();
		AdminUserVo adminUserVo=adminUserReaderMapper.selectAdminUserById(id);
		if(adminUserVo==null){
			result.setMsgCode(Const.ERROR_USER_NULL);
			result.setComment("用户不存在");
			return result;
		}
		result.setOk(true);
		result.setComment(Const.SUCCESS_CODE);
		result.setData(adminUserVo);
		return result;
	}

	@Override
	public List<AdminUserVo> selectRoleNameByRoleType(String roleType) {
		return adminRoleReaderMapper.selectRoleNameByRoleType(roleType);
	}
}
