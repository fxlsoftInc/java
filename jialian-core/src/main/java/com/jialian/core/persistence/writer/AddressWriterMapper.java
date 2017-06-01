package com.jialian.core.persistence.writer;

import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.Where;

import org.apache.ibatis.annotations.Param;

public interface AddressWriterMapper {
    int deleteByWhere(Where where);

    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    int insertSelective(Address record);

    /**
    * @Description: 添加地址(返回id)
    * @param @param record
    * @param @return
    * @return int
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2015年12月19日 下午2:45:37
     */
    int insertSelectiveBackId(Address record);
    
    int updateByWhereSelective(@Param("record") Address record, @Param("where") Where where);

    int updateByWhere(@Param("record") Address record, @Param("where") Where where);

    int updateByPrimaryKeySelective(@Param("record") Address record);

    int updateByPrimaryKey(@Param("record") Address record);

    /**
     *@Description: o2o个人中心--添加联系人收货地址
     *@Author: shx  408640463@qq.com
     *@Since:2015年12月16日 上午11:57:18
     *@Version:1.0
     */
	int insertLinkAddress( Address record);
    
}