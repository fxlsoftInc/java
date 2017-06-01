package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface AdminPermissionWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(AdminPermission record);

    int insertSelective(AdminPermission record);

    int updateByWhereSelective(@Param("record") AdminPermission record, @Param("where") Where where);

    int updateByWhere(@Param("record") AdminPermission record, @Param("where") Where where);

    int updateByPrimaryKeySelective(AdminPermission record);

    int updateByPrimaryKey(AdminPermission record);
}