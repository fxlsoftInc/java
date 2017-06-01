package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.AdminRule;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface AdminRuleWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);
    
    int deleteByRoleId(Long roleId);

    int insert(AdminRule record);

    int insertSelective(AdminRule record);

    int updateByWhereSelective(@Param("record") AdminRule record, @Param("where") Where where);

    int updateByWhere(@Param("record") AdminRule record, @Param("where") Where where);

    int updateByPrimaryKeySelective(AdminRule record);

    int updateByPrimaryKey(AdminRule record);
}