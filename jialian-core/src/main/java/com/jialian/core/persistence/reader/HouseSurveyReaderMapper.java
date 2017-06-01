package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.HouseSurvey;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface HouseSurveyReaderMapper {
    int countByWhere(Where where);

    List<HouseSurvey> selectByWhere(Where where);

    HouseSurvey selectByPrimaryKey(Long id);
}