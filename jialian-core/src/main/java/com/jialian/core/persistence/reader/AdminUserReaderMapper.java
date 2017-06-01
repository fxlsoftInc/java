package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.AdminRoleQuery;
import com.jialian.api.domain.query.AdminUserQuery;
import com.jialian.api.domain.vo.AdminUserVo;

public interface AdminUserReaderMapper {
    int countByWhere(Where where);

    List<AdminUser> selectByWhere(Where where);

    AdminUser selectByPrimaryKey(Long id);

	List<AdminUserVo> selectAdminRoleList(@Param("query")AdminRoleQuery query);
	
	int selectAdminUserCountByQuery(@Param("query")AdminUserQuery query);
	
	List<AdminUserVo> selectAdminUserListByQuery(@Param("query")AdminUserQuery query);
	
	AdminUserVo selectAdminUserById(@Param("id")Long id);

}