package com.jialian.api.service;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.CaseHouse;
import com.jialian.api.domain.entity.CaseHouseInfo;
import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.query.CaseHouseQuery;
import com.jialian.api.domain.vo.CaseHouseVo;

public interface CaseHouseServiceApi {

	/**
	 * @Description: 案例房查询（分页）
	 * @param: @param query 查询封装类
	 * @param: @param currentPage
	 * @param: @param onePageCount
	 * @param: @return   
	 * @return: ServiceResult<Page<CaseHouse>>   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	ServiceResult<Page<CaseHouseVo>> getCaseHouseListByQuery(CaseHouseQuery query);
	
	/**
	* @Description: 获取案例馆信息
	* @param @param id 案例馆id
	* @param @return
	* @return ServiceResult<CaseHouse>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月17日 下午2:04:28
	 */
	ServiceResult<CaseHouseVo> getCaseHouse(Long id,Long userId);
	
	/**
	* @Description: 案例馆添加
	* @param @param caseHouse 案例馆
	* @param @param caseHouseInfo 案例馆详情
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月19日 下午2:39:55
	 */
	ServiceResult<Object> addCaseHouse(CaseHouse caseHouse,CaseHouseInfo caseHouseInfo,HouseInfo houseInfo,long[] resourceInfoIds);
	
	/**
	* @Description: 案例馆修改
	* @param @param caseHouse 案例馆
	* @param @param caseHouseInfo 案例管详情
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月19日 下午3:05:01
	 */
	ServiceResult<Object> updateCaseHouse(CaseHouse caseHouse,CaseHouseInfo caseHouseInfo,HouseInfo houseInfo,List<ResourceInfo> infos);
	
	/**
	* @Description: 案例馆收藏
	* @param @param id
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月28日 下午4:55:58
	 */
	ServiceResult<Object> addCollecCaseHouse(Long userId,Long id);
}
