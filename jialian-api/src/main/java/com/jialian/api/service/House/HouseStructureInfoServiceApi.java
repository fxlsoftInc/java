package com.jialian.api.service.House;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.Where;

public interface HouseStructureInfoServiceApi {

	/**
	* @Description: 房屋结构信息修改
	* @param @param houseStructureInfo
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午6:25:18
	 */
	public ServiceResult<Object> updateHouseStructureInfo(HouseStructureInfo houseStructureInfo);
	
	/**
	* @Description: 房屋结构信息添加
	* @param @param houseStructureInfo
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午5:23:47
	 */
	public ServiceResult<Object> addHouseStructureInfo(HouseStructureInfo houseStructureInfo);
	
	int countByWhere(Where where);

    List<HouseStructureInfo> selectByWhere(Where where);
    
    int updateByWhereSelective(HouseStructureInfo record, Where where);

    int updateByWhere(HouseStructureInfo record, Where where);

    int updateByPrimaryKeySelective(HouseStructureInfo record);

    int updateByPrimaryKey(HouseStructureInfo record);

    HouseStructureInfo selectByPrimaryKey(Long id);
}
