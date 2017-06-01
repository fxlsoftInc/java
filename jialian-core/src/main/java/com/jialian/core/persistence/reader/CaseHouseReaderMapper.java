package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.CaseHouse;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.CaseHouseQuery;
import com.jialian.api.domain.vo.CaseHouseVo;
import com.jialian.api.domain.vo.HomeVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CaseHouseReaderMapper {
    int countByWhere(Where where);

    List<CaseHouse> selectByWhereWithBLOBs(Where where);

    List<CaseHouse> selectByWhere(Where where);

    CaseHouse selectByPrimaryKey(Long id);
    
    int getCaseHouseCountByQuery(@Param("query")CaseHouseQuery query);
    
    List<CaseHouseVo> getCaseHouseListByQuery(@Param("query")CaseHouseQuery query);
    
    List<HomeVo> selectHomeCase();
    
    CaseHouseVo selectCaseHouseVo(@Param("id")Long id);
}