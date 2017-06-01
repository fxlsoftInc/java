package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.ComboQuery;
import com.jialian.api.domain.vo.ComboComplexVO;
import com.jialian.api.domain.vo.ComboVo;

public interface ComboReaderMapper {
    int countByWhere(Where where);

    List<Combo> selectByWhere(Where where);

    Combo selectByPrimaryKey(Long id);
    
    Combo selectSimpleByPrimaryKey(Long id);
    
    ComboComplexVO selectComplexComboByPrimaryKey(Long id);
    
    List<ComboVo> selectComboListById(@Param("id")Long id);

    int selectComboCountByQuery(@Param("query")ComboQuery query);
    
    List<Combo> selectComboListByQuery(@Param("query")ComboQuery query);

	List<Combo> selectComboList();
	
	List<Combo> selectComboAll();
}