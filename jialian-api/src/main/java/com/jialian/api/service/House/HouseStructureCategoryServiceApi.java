package com.jialian.api.service.House;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureCategory;
import com.jialian.api.domain.entity.Where;

public interface HouseStructureCategoryServiceApi {

	/**
	* @Description: 房屋结构分类信息修改
	* @param @param category
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午6:02:00
	 */
	public ServiceResult<Object> updateHouseStructureCategory(HouseStructureCategory category);
	
	/**
	* @Description: 房屋结构分类信息添加
	* @param @param category
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午5:12:34
	 */
	public ServiceResult<Object> addHouseStructureCategory(HouseStructureCategory category);
	
	int countByWhere(Where where);

    List<HouseStructureCategory> selectByWhere(Where where);

    HouseStructureCategory selectByPrimaryKey(Long id);
}
