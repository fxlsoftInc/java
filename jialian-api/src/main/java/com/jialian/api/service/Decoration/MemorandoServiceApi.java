package com.jialian.api.service.Decoration;

import java.util.List;

import com.jialian.api.domain.entity.Memorando;
import com.jialian.api.domain.entity.Where;

public interface MemorandoServiceApi {

	int countByWhere(Where where);

    List<Memorando> selectByWhere(Where where);

    Memorando selectByPrimaryKey(Long id);
    
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    Memorando insert(Memorando record);

    Memorando insertSelective(Memorando record);

    int updateByWhereSelective(Memorando record, Where where);

    int updateByWhere(Memorando record, Where where);

    int updateByPrimaryKeySelective(Memorando record);

    int updateByPrimaryKey(Memorando record);
}
