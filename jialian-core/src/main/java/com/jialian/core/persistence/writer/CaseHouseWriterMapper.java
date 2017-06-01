package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.CaseHouse;
import com.jialian.api.domain.entity.Where;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(CaseHouse record);

    int insertSelective(CaseHouse record);
    
    /**
    * @Description: 添加案例馆(返回id)
    * @param @param record
    * @param @return
    * @return int
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2015年12月19日 下午2:48:43
     */
    int insertSelectiveBackId(CaseHouse record);

    int updateByWhereSelective(@Param("record") CaseHouse record, @Param("where") Where where);

    int updateByWhereWithBLOBs(@Param("record") CaseHouse record, @Param("where") Where where);

    int updateByWhere(@Param("record") CaseHouse record, @Param("where") Where where);

    int updateByPrimaryKeySelective(CaseHouse record);

    int updateByPrimaryKeyWithBLOBs(CaseHouse record);

    int updateByPrimaryKey(CaseHouse record);
}