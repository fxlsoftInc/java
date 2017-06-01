package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.SystemMessageVo;

import org.apache.ibatis.annotations.Param;

public interface SystemMessageWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemMessage record);

    int insertSelective(SystemMessage record);

    int updateByWhereSelective(@Param("record") SystemMessage record, @Param("where") Where where);

    int updateByWhere(@Param("record") SystemMessage record, @Param("where") Where where);

    int updateByPrimaryKeySelective(SystemMessage record);

    int updateByPrimaryKey(SystemMessage record);

	int insertSystemMessage(SystemMessageVo systemMessageVo);
}