package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.HouseSurvey;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface HouseSurveyWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(HouseSurvey record);

    int insertSelective(HouseSurvey record);

    int updateByWhereSelective(@Param("record") HouseSurvey record, @Param("where") Where where);

    int updateByWhere(@Param("record") HouseSurvey record, @Param("where") Where where);

    int updateByPrimaryKeySelective(HouseSurvey record);

    int updateByPrimaryKey(HouseSurvey record);
}