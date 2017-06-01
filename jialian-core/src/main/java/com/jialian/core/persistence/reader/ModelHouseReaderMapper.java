package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.ModelHouse;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.ModelHouseQuery;
import com.jialian.api.domain.vo.ModelHouseDetailVo;
import com.jialian.api.domain.vo.ModelHouseVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ModelHouseReaderMapper {
    int countByWhere(Where where);

    List<ModelHouse> selectByWhere(Where where);

    ModelHouse selectByPrimaryKey(Long id);
    
    int getModelHouseCountByQuery(@Param("query")ModelHouseQuery query);
    
    List<ModelHouseVo> getModelHouseListByQuery(@Param("query")ModelHouseQuery query);

	ModelHouseDetailVo selectModelHouseDetail(Long id);
}