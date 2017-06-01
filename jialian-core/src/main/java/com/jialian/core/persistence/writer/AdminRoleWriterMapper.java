package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    int updateByWhereSelective(@Param("record") AdminRole record, @Param("where") Where where);

    int updateByWhere(@Param("record") AdminRole record, @Param("where") Where where);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}