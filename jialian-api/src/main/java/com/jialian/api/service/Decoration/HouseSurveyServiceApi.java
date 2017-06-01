package com.jialian.api.service.Decoration;

import java.util.List;

import com.jialian.api.domain.entity.HouseSurvey;
import com.jialian.api.domain.entity.Where;

public interface HouseSurveyServiceApi {
	
	int countByWhere(Where where);

    List<HouseSurvey> selectByWhere(Where where);

    HouseSurvey selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    HouseSurvey insert(HouseSurvey record);

    HouseSurvey insertSelective(HouseSurvey record);

    int updateByWhereSelective(HouseSurvey record, Where where);

    int updateByWhere(HouseSurvey record, Where where);

    int updateByPrimaryKeySelective(HouseSurvey record);

    int updateByPrimaryKey(HouseSurvey record);
}
