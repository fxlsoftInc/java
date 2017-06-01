package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface UserWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByWhereSelective(@Param("record") User record, @Param("where") Where where);

    int updateByWhereWithBLOBs(@Param("record") User record, @Param("where") Where where);

    int updateByWhere(@Param("record") User record, @Param("where") Where where);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

	int updateByNewPsd(User record);
}