package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Where;

import org.apache.ibatis.annotations.Param;

public interface AdminUserWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    int updateByWhereSelective(@Param("record") AdminUser record, @Param("where") Where where);

    int updateByWhere(@Param("record") AdminUser record, @Param("where") Where where);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

}