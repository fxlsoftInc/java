package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.AdminPermissionQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdminPermissionReaderMapper {
    int countByWhere(Where where);

    List<AdminPermission> selectByWhere(Where where);

    AdminPermission selectByPrimaryKey(Long id);
    
    List<AdminPermission> selectByUserId(Long userId);
    
    List<AdminPermission> selectAdminPermissionList(@Param("query")AdminPermissionQuery query);
    
    List<AdminPermission> selectAdminPermissionByRoleId(@Param("roleId")Long roleId);
}