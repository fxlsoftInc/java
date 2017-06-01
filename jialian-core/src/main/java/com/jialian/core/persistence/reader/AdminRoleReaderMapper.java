package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.RoleQuery;
import com.jialian.api.domain.vo.AdminUserVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdminRoleReaderMapper {
    int countByWhere(Where where);

    List<AdminRole> selectByWhere(Where where);

    AdminRole selectByPrimaryKey(Long id);
    
    int selectAdminRoleCountByQuery(@Param("query")RoleQuery query);
    
    List<AdminRole> selectAdminRoleListByQuery(@Param("query")RoleQuery query);
    
    List<AdminRole> selectAdminRoleList(@Param("query")RoleQuery query);

	List<AdminUserVo> selectRoleNameByRoleType(@Param("roleType")String roleType);
}